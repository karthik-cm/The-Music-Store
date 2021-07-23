package com.musicstore.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.dao.CommonDao;
import com.musicstore.dao.SignUpDao;
import com.musicstore.utils.MusicStoreUtils;

@Controller
public class SignUpController {
	private static final Logger logger = Logger.getLogger(SignUpController.class);	
	
	private SignUpDao signUpDao;

	public SignUpDao getSignUpDao() {
		return signUpDao;
	}

	public void setSignUpDao(SignUpDao signUpDao) {
		this.signUpDao = signUpDao;
	}


	/** To check user name availability
	 */
	@RequestMapping(value=MusicStoreConstants.CHECK_USERNAME_AVAILABILITY, method=RequestMethod.POST)
	@ResponseBody
	public String checkUserNameAvailablity(@RequestParam String userName) {
		String isUserNameAvailable = "N";
		try {
			logger.info("\n\n");
			logger.info("Inside checkUserNameAvailablity() :::: SignUpController - userName : "+userName);
			logger.info("Check username availability");
			
			isUserNameAvailable = signUpDao.checkUserNameAvailability(userName);
			logger.info("Is Username : "+userName +" available : "+isUserNameAvailable);
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in checkUserNameAvailablity() :::: SignUpController : "+e); 
		}
		return isUserNameAvailable;
	}
	
	
	/** Sign Up : New User
	 */
	@RequestMapping(value=MusicStoreConstants.SIGN_UP, method=RequestMethod.POST)
	@ResponseBody
	public String newUserSignUp(HttpServletRequest request, HttpSession session) {
		JSONObject responseDetails = new JSONObject();
		try {
			logger.info("\n\n");
			logger.info("Inside newUserSignUp() :::: SignUpController ");
			logger.info("New user Sign Up");
			
			Map<String, String[]> mapRequestParams = request.getParameterMap();
			JSONObject newUserDetails = MusicStoreUtils.mapToJsonObject(mapRequestParams);
			logger.info("New user details from form : "+newUserDetails);
			
			// Add new user details
			JSONObject addNewUserDetails = signUpDao.addNewUserDetails(newUserDetails);
			String isNewUserFormValid = addNewUserDetails.has("isNewUserFormValid") ? addNewUserDetails.getString("isNewUserFormValid") : null;
			String isNewUserAdded = addNewUserDetails.has("isNewUserAdded") ? addNewUserDetails.getString("isNewUserAdded") : null;
			
			if(isNewUserFormValid != null && isNewUserFormValid.equals("Y")) {
				if(isNewUserAdded != null && isNewUserAdded.equals("Y")) {
					responseDetails.put("status", "Y");
					
					// Get user details from DB
					String userName = addNewUserDetails.getString("username");
					JSONObject userDetails = CommonDao.getMsUserDetails(userName);
					session.setAttribute(MusicStoreConstants.MS_USER_DETAILS, userDetails);
				}
				else {
					responseDetails.put("status", "N");
					responseDetails.put("msg", MusicStoreConstants.GENERIC_ERROR_MSG);
				}
			}
			else {
				responseDetails.put("status", "N");
				responseDetails.put("msg", MusicStoreConstants.INVALID_FORM_DETAILS_ERROR_MSG);
			}
		}
		catch(JSONException e) {
			logger.error("\n\nJSONException occurred in newUserSignUp() :::: SignUpController : "+e); 
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in newUserSignUp() :::: SignUpController : "+e); 
		}
		return responseDetails.toString();
	}
}
