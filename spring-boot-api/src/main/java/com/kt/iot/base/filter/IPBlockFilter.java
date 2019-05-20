/*
 * GiGA IoT Platform version 2.0
 *
 *  Copyright ⓒ 2015 kt corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actual or
 *  intended publication of such software.
 */

package com.kt.iot.base.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;


@Component
public class IPBlockFilter implements Filter {
	
	 private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${ip.block.white.list}")
    private String ips;

    @Value("${ip.block:true}")
    private boolean isBlock;

    private String[] whiteIP;

    public void init(FilterConfig config) throws ServletException {
    	logger.info("IP BLOCK : " + isBlock);
    	logger.info("IP BLOCK WHITE LIST : " + ips);

        if(isBlock) {
            whiteIP = ips.split(",");
        }
    }

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if(isBlock) {
            //HttpServletRequest servletRequest = (HttpServletRequest) request;
            String ip = request.getRemoteAddr();

            for(String wip:whiteIP) {
                if(ip.equals(wip)) {
                    chain.doFilter(request, response);
                    break;
                }
            }
        }else{
            chain.doFilter(request, response);
        }
    }

    public void destroy() {

    }
}
