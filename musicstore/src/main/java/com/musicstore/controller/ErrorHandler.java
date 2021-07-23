package com.musicstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.utils.MusicStoreUtils;

@Controller
public class ErrorHandler {
	private static final Logger logger = Logger.getLogger(ErrorHandler.class);
	
	/* HTTP Status Code : 
	 * 
	 * 2xx  :  success
	 * 3xx  :  redirection
	 * 4xx  :  cliet errors
	 * 
	 */
	
	
	@RequestMapping(value=MusicStoreConstants.HANDLE_ERROR, method=RequestMethod.GET)
	private ModelAndView handleError404(HttpServletRequest request, ModelAndView mv) {
		logger.info("Inside handleError404() ::::  ErrorHandler");
		String httpErrorDesc = "";
		
		int statusCode = MusicStoreUtils.getErrorCode(request);
		switch(statusCode) {
			case 400 : {
				httpErrorDesc = "Bad Request";
				break;
			}
			case 401 : {
				httpErrorDesc = "Unauthorized";
				break;
			}
			case 404 : {
				httpErrorDesc = "Resource Not Found";
				break;
			}
			case 405 : {
				httpErrorDesc = "Method Not Allowed";
				break;
			}
			case 500 : {
				httpErrorDesc = "Internal Server Error";
				break;
			}
			default : {
				httpErrorDesc = "Some Error";
				break;
			}
		}
		
		logger.info("Http Status Code: "+statusCode +" " +httpErrorDesc);
		
		mv.setViewName(MusicStoreConstants.MS_HANDLE_ERROR_PORTLET);
		return mv;
	}
	
}
