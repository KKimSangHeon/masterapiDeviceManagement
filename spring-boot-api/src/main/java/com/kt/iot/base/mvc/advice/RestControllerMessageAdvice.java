package com.kt.iot.base.mvc.advice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.kt.iot.Version;
import com.kt.iot.base.exception.BadRequestException;
import com.kt.iot.base.exception.BaseException;
import com.kt.iot.base.exception.GatewayTimeoutException;
import com.kt.iot.base.exception.InternalServerErrorException;
import com.kt.iot.base.exception.NotFoundException;
import com.kt.iot.base.exception.UnauthorizedException;
import com.kt.iot.base.exception.UnprocessableEntityException;
import com.kt.iot.base.interceptor.LoggingHandlerInterceptor;
import com.kt.iot.base.message.BaseResponse;
import com.kt.iot.base.message.ResponseCode;
import com.kt.iot.base.mvc.message.Message;
import com.kt.iot.base.mvc.message.MessageAccessor;

/**
 * RestControler에 적용되는 ControllerAdvice로서 BaseResponse로 message나 데이터를 wrapping하여 클라이언트에 전달하는 데이터 골격을 생성한다.
 * @author jeado
 */
@ControllerAdvice(annotations = RestController.class)
public class RestControllerMessageAdvice implements ResponseBodyAdvice<Object> {

	private static final Logger logger = LoggerFactory.getLogger(RestControllerMessageAdvice.class);

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#supports(org.springframework.core.MethodParameter, java.lang.Class)
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO 이부분은 바꿔야함.
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#beforeBodyWrite(java.lang.Object, org.springframework.core.MethodParameter, org.springframework.http.MediaType, java.lang.Class, org.springframework.http.server.ServerHttpRequest, org.springframework.http.server.ServerHttpResponse)
	 */
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if (body instanceof BaseResponse) {
			if (!request.getURI().toString().contains(Version.V11)) {
				String responseCode = ((BaseResponse) body).getResponseCode();
				if (!responseCode.equals("OK") && !responseCode.equals("NG")) {
					if (responseCode.equals(ResponseCode.OK.toString())) {
						((BaseResponse) body).setResponseOK();
					} else {
						((BaseResponse) body).setResponseNG();
					}
				}
			}
			return body;
		} else {
			if (!request.getURI().toString().contains(Version.V12)) {
				BaseResponse baseResponse = new BaseResponse();
				MessageAccessor messageAccessor = null;
				if (request instanceof ServletServerHttpRequest) {
					HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
					messageAccessor = (MessageAccessor) servletRequest.getAttribute("messages");
				}
				if(messageAccessor != null && messageAccessor.getMessageList().size() > 0) {
					Message message = messageAccessor.getMessageList().get(0);
					baseResponse.setResponseCode(message.getCode());
					baseResponse.setMessage(message.getMsg());
					baseResponse.setPaging(message.getPaging());
				}
				baseResponse.setData(body);
				return baseResponse;
			} else {
				return body;
			}
		}
	}

	/**
	 * 기본 예외처리 헨들러, 다른 예외처리 헨들러에서 처리되지 않은 예외들을 처리함.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResponse> handleOtherExceptions(Exception ex, HttpServletRequest request) {

		logProcess(ex,request);
		
		HttpStatus httpStatus = HttpStatus.OK;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		BaseResponse baseResponse = new BaseResponse();
		// Version 1.1 
		if (request.getRequestURI().contains(Version.V11)) {
			if (ex instanceof BaseException) {
				baseResponse.setResponseCode(((BaseException) ex).getCode());
				
				// Exception 에 message 가 있는 경우 Response 의 기존 baseResponse.getMessage() 에  Exception 의 Message 를 추가함
				String message = ex.getMessage();
				if (message != null) baseResponse.setMessage(baseResponse.getMessage() + " - " + message);
				
				if (ex instanceof UnauthorizedException) {
					httpStatus = HttpStatus.UNAUTHORIZED;
				} else if (ex instanceof GatewayTimeoutException) {
					httpStatus = HttpStatus.GATEWAY_TIMEOUT;
					baseResponse.setData(((GatewayTimeoutException) ex).getData());
				} else if (ex instanceof BadRequestException) {
					httpStatus = HttpStatus.BAD_REQUEST;
				} else if (ex instanceof NotFoundException) {
					httpStatus = HttpStatus.NOT_FOUND;
				} else if (ex instanceof UnprocessableEntityException) {
					httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
				} else if (ex instanceof InternalServerErrorException) {
					httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				}
			// 파라미터 데이터 타입이 다른 경우
			} else if (ex instanceof TypeMismatchException) {
				httpStatus = HttpStatus.BAD_REQUEST;
				baseResponse.setResponseCode(ResponseCode.INTERNAL_INVALID_MESSAGE_FORMAT);
			// JSON 메시지 포맷이 다른 경우
			} else if (ex instanceof HttpMessageNotReadableException) {
				httpStatus = HttpStatus.BAD_REQUEST;
				baseResponse.setResponseCode(ResponseCode.INTERNAL_INVALID_MESSAGE_FORMAT);
			// DB ERROR
			} else if (ex instanceof DataAccessException) {
				httpStatus = HttpStatus.BAD_REQUEST;
				PSQLException psqlException = (PSQLException) ((DataAccessException) ex).getRootCause();
				// 필수 값이 없는 경우 
				if ("23502".equals(psqlException.getSQLState())) {
					baseResponse.setResponseCode(ResponseCode.INTERNAL_MANDATORY_PARAMETER_MISSING);
				// 참조하는 값이 다른 경우
				} else if ("23503".equals(psqlException.getSQLState())) {
					baseResponse.setResponseCode(ResponseCode.INTERNAL_ASSOCIATED_PARAMETER_NOT_FOUND);
				// 키 중복이 발생 하는 경우
				} else if ("23505".equals(psqlException.getSQLState())) {
					baseResponse.setResponseCode(ResponseCode.INTERNAL_DUPLICATE_PARAMETER);
				// 파라미터 데이터 타입이 다른 경우
				} else if ("22P02".equals(psqlException.getSQLState())) {
					baseResponse.setResponseCode(ResponseCode.INTERNAL_TYPE_MISMATCH);
				} else {
					httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
					baseResponse.setResponseCode(ResponseCode.INTERNAL_SERVER_ERROR);
				}
			} else {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				baseResponse.setResponseCode(ResponseCode.INTERNAL_SERVER_ERROR);
			}
		// Version 1.0
		} else {
			baseResponse.setResponseNG();
			baseResponse.setMessage(ex.getMessage());
			if (ex instanceof BaseException) {
				if (ex instanceof UnauthorizedException) {
					httpStatus = HttpStatus.UNAUTHORIZED;
					baseResponse.setResponseCode(((BaseException) ex).getCode());
				}
			}
		}

		return new ResponseEntity<BaseResponse>(baseResponse, headers, httpStatus);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BaseResponse> handleRequestValidationExceptions(MethodArgumentNotValidException ex) {
	    // Request parameter 에 필수값 없을 때
	    
	    BaseResponse baseResponse = new BaseResponse();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	    
	    baseResponse.setResponseCode(ResponseCode.INTERNAL_INVALID_MESSAGE_FORMAT);
	    
	    String message = ex.getBindingResult().getFieldError().getField() + ", " + ex.getBindingResult().getFieldError().getDefaultMessage();
	    if (message != null) baseResponse.setMessage(baseResponse.getMessage() + " - " + message);
	    
	    return new ResponseEntity<BaseResponse>(baseResponse, headers, httpStatus);
	}
	
	private void logProcess(Exception ex, HttpServletRequest request) {
    	LocalDateTime endDateTime = LocalDateTime.now();
        LocalDateTime startDateTime = (LocalDateTime)request.getAttribute(LoggingHandlerInterceptor.START_TIME);
        String userId = (String)request.getAttribute(LoggingHandlerInterceptor.USER_ID);
        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null)
        	ip = request.getRemoteAddr();
        Period betweenDates = Period.fieldDifference(startDateTime, endDateTime);

		logger.info(getMatchedString(url) + "|" + url + "|" + method + "|" + startDateTime.toString("yyyy-MM-dd HH:mm:ss.SSS") + "|" + endDateTime.toString("yyyy-MM-dd HH:mm:ss.SSS") + "|" + betweenDates.getMillis() + "|" + ip + "|" + userId + "|" + ex.getClass().toString());
		logger.error(ex.getMessage(), ex);
	}

	private String getMatchedString(String targetString) {
		Pattern patt = Pattern.compile("http(s)?:\\/\\/[1-90\\.a-zA-Z]+(:\\d{2,5})((\\/\\w+){3,5})");
		Matcher match = patt.matcher(targetString);
		String matchedString = "";
		if (match.find()) {
			matchedString = match.group(3);
		}
		return matchedString;
	}

}
