package com.musicstore.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.dao.LoginLogoutDao;
import com.musicstore.utils.SessionUtil;

@Controller
public class LoginLogoutController {
	private static final Logger logger = Logger.getLogger(LoginLogoutController.class);
	
	private LoginLogoutDao loginLogoutDao;
	
	public LoginLogoutDao getLoginLogoutDao() {
		return loginLogoutDao;
	}
	public void setLoginLogoutDao(LoginLogoutDao loginLogoutDao) {
		this.loginLogoutDao = loginLogoutDao;
	}

	/** Login to Music-Store
	*/
	@RequestMapping(value=MusicStoreConstants.LOGIN, method=RequestMethod.POST)
	@ResponseBody
	public String loginToMusicStore(HttpServletRequest request, HttpSession session) {
		logger.info("\n\nInside loginToMusicStore() :::: LoginLogoutController");
		logger.info("Login to Music Store");
		
		Map<String, String[]> requestParamsMap = request.getParameterMap();
		JSONObject loginDetails = new JSONObject();
		
		try {
			String LOGIN_USERNAME = "username";
			String LOGIN_PASSWORD = "password";
			String username = null, password = null;
			
			if(requestParamsMap.containsKey(LOGIN_USERNAME)) {
				username = requestParamsMap.get(LOGIN_USERNAME)[0];
			}
			if(requestParamsMap.containsKey(LOGIN_PASSWORD)) {
				password = requestParamsMap.get(LOGIN_PASSWORD)[0];
			}
			logger.info("Credentials entered :: Username = "+username+", password : "+password);
			
			if(username != null && username.length() > 0 && password != null && password.length() > 0) {
				// Validate user login
				JSONObject userDetails = loginLogoutDao.validateUserLoginCredentials(username, password);
				logger.info("Final user details : "+userDetails);
				
				if(userDetails != null && userDetails.has("user_id")) { // Login Success
					// Set results in Session
					SessionUtil.setAttibute(request, MusicStoreConstants.MS_USER_DETAILS, userDetails);
					SessionUtil.setAttibute(request, MusicStoreConstants.MS_VALID_LOGIN, "Y");
					loginDetails.put("status", "Y");
				}
				else {
					logger.info("Invalid Credentials. Login to Music Store failed.");
					loginDetails.put("status", "N");
					loginDetails.put("msg", MusicStoreConstants.INVALID_CREDENTIALS_ERROR_MSG);
				}
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in loginToMusicStore() :::: LoginLogoutController : "+e);
		}
		
		logger.info("Returning from loginToMusicStore() :::: LoginLogoutController");
		return loginDetails.toString();
	}
	
	
	/** Logout from MusicStore
	 */
	@RequestMapping(value=MusicStoreConstants.LOGOUT, method=RequestMethod.GET)
	public String logoutFromMusicStore(HttpServletRequest request) {
		logger.info("\n\nInside logoutFromMusicStore() :::: LoginLogoutController ");
		logger.info("Logout from Music Store");
		HttpSession session = request.getSession();
		if(session != null) {
			JSONObject userDetails = (JSONObject) SessionUtil.getAttribute(request, MusicStoreConstants.MS_USER_DETAILS);
			
			 // Update logout time
			loginLogoutDao.updateLogoutTime(userDetails);
			
			// Invalidate current session
			session.invalidate();
		}
		logger.info("Successfully logged out user. Redirecting to Home page");
		return MusicStoreConstants.MS_REDIRECT_TO_HOME;
	}
}

