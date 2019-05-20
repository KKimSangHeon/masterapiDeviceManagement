package com.kt.iot.base.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kt.iot.base.util.ObjectConverter;

public class LoggingHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingHandlerInterceptor.class);

    public static String START_TIME = "@@START_TIME";
    public static String USER_ID = "@@USER_ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	LocalDateTime startDateTime = LocalDateTime.now();
        request.setAttribute(START_TIME, startDateTime);

//        logger.debug("URI : " + request.getRequestURI());
        String header = request.getHeader("authorization");
        if (header != null){
            header = header.replaceAll("Bearer ", "");
            Jwt jwt = null;
            try {
                jwt = JwtHelper.decode(header);
                JSONObject jsonObject = ObjectConverter.toJSON(jwt.getClaims());
//                logger.debug("************** :jsonObject *****" + jsonObject);
                if (jsonObject.get("mbr_id") != null)
                	request.setAttribute(USER_ID, jsonObject.get("mbr_id").toString());
                else
                	request.setAttribute(USER_ID, "guest");

            } catch (Exception ex) {
            	logger.debug(ex.toString());
            }

      }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    	LocalDateTime endDateTime = LocalDateTime.now();
        LocalDateTime startDateTime = (LocalDateTime)request.getAttribute(START_TIME);
        String userId = (String)request.getAttribute(USER_ID);
        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null)
        	ip = request.getRemoteAddr();
        Period betweenDates = Period.fieldDifference(startDateTime, endDateTime);
        logger.info(getMatchedString(url) + "|" + url + "|" + method + "|" + startDateTime.toString("yyyy-MM-dd HH:mm:ss.SSS") + "|" + endDateTime.toString("yyyy-MM-dd HH:mm:ss.SSS") + "|" + betweenDates.getMillis() + "|" + ip + "|" + userId);
    }

	private String getMatchedString(String targetString) {
		Pattern patt = Pattern.compile("http(s)?:\\/\\/[1-90\\.a-zA-Z]+(:\\d{2,5})((\\/\\w+){3,10})");
		Matcher match = patt.matcher(targetString);
		String matchedString = "";
		if (match.find()) {
			matchedString = match.group(3);
		}
		return matchedString;
	}

}
