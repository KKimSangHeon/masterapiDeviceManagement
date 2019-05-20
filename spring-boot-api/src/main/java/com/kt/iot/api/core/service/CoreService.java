///**
// * <PRE>
// *  Project 3MP.master-api
// *  Package com.kt.iot.api.core.service
// * </PRE>
// * @brief
// * @file CoreService.java
// * @date 2016. 1. 4. 오후 6:58:00
// * @author kim.seokhun@kt.com
// *  변경이력
// *        이름     : 일자          : 근거자료   : 변경내용
// *       ------------------------------------
// *        kim.seokhun@kt.com  : 2016. 1. 4.       :            : 신규 개발.
// *
// * Copyright © 2013 kt corp. all rights reserved.
// */
//package com.kt.iot.api.core.service;
//
//import java.net.URI;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//
//import com.kt.iot.base.message.BaseResponse;
//
///**
// * <PRE>
// *  ClassName CoreService
// * </PRE>
// * @brief
// * @version 1.0
// * @date 2016. 1. 4. 오후 6:58:00
// * @author kim.seokhun@kt.com
// */
//@Service
//public class CoreService {
//
//	private static final Logger logger = LoggerFactory.getLogger(CoreService.class);
//
//	@Value("${core.url}")
//	private String coreUrl;
//	@Value("${core.contextPath}")
//	private String contextPath;
//	@Value("${core.url.updateMember}")
//	private String updateMember;
//
//	public void updateMember() {
//
//		Map<String, String> param = new HashMap<String, String>();
//		
//
//		URI requestUri = getUrl(coreUrl + contextPath, updateMember, param);
//
//		logger.info("Update member : " + requestUri.toString());
//
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.getForObject(requestUri, BaseResponse.class);
////		restTemplate.postForObject(getUrl(coreUrl + contextPath, updateMember), getRequestEntity(group), null);
//	}
//
//	protected URI getUrl(String baseUrl, String path) {
//        return getUrl(baseUrl, path, null);
//    }
//
//    protected URI getUrl(String baseUrl, String path, Map<String, String> param) {
//        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl).path(path);
//        if (param != null) {
//            Iterator<String> iteratortor = param.keySet().iterator();
//            while (iteratortor.hasNext()) {
//                String key = (String) iteratortor.next();
//                builder.queryParam(key, param.get(key));
//            }
//        }
//        return builder.build().toUri();
//    }
//
//    protected <T> HttpEntity<T> getRequestEntity(T body) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        return new HttpEntity<T>(body, headers);
//    }
//}
