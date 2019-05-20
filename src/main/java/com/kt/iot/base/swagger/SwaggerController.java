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

package com.kt.iot.base.swagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

@Controller
public class SwaggerController {
			
	@ApiIgnore
	@RequestMapping(value = "/swagger")
	public ModelAndView ui(ModelAndView mav) {
	        mav.setViewName("/swagger-ui/index");
	        return mav;
	}
	
	@ApiIgnore
	@RequestMapping(value = "/api-docs")
	public ModelAndView docs(ModelAndView mav) {
	        mav.setViewName("/swagger-ui/api-docs");
	        return mav;
	}
}
