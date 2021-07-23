package com.musicstore.utils;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.musicstore.constants.MusicStoreConstants;

public class MusicStoreUtils {
	
	private static final Logger logger = Logger.getLogger(MusicStoreUtils.class);
	
	public static JSONObject mapToJsonObject(Map<String, String[]> requestParamMap) {
		logger.info("\n\nInside mapToJsonObject() :::: MusicStoreUtils");
		JSONObject requestParamsObj = new JSONObject();
		for(Map.Entry<String, String[]> mapEntry : requestParamMap.entrySet()) {
			requestParamsObj.put(mapEntry.getKey(), mapEntry.getValue()[0]);
		}
		logger.info("Final JSONObject : "+requestParamsObj);
		return requestParamsObj;
	}
	
	
	public static String randomKeyGenerator(int keySize) {
		StringBuffer randomKey = new StringBuffer();
		String alphaNum[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9"};
		
		Random ran = new Random();
		for(int i=1; i<=keySize; i++) {
			int randomArrayIndex = ran.nextInt(alphaNum.length);
			randomKey.append(alphaNum[randomArrayIndex]);
		}
		return randomKey.toString();
	}
	
	
	public static boolean authenticateLogin(HttpServletRequest request) {
		boolean isValidLogin = false;
		try {
			String validLogin = (String) SessionUtil.getAttribute(request, MusicStoreConstants.MS_VALID_LOGIN);
			if(validLogin != null && validLogin.equalsIgnoreCase("Y")) {
				isValidLogin = true;
				logger.info("Check valid login : "+isValidLogin);
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in authenticateLogin() :::: MusicStoreUtils : "+e);
		}
		return isValidLogin;
	}
	
	
	public static int getErrorCode(HttpServletRequest request) {
		return (Integer) request.getAttribute(MusicStoreConstants.HTTP_ERROR_STATUS_CODE);
	}
	
}
