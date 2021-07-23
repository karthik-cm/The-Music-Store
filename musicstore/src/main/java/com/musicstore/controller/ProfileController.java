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
import org.springframework.web.servlet.ModelAndView;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.dao.CommonDao;
import com.musicstore.dao.ProfileDao;
import com.musicstore.utils.MusicStoreUtils;

@Controller
public class ProfileController {
	private static final Logger logger = Logger.getLogger(ProfileController.class);
	
	private ProfileDao profileDao;
	
	public ProfileDao getProfileDao() {
		return profileDao;
	}

	public void setProfileDao(ProfileDao profileDao) {
		this.profileDao = profileDao;
	}

	private static final String FIRST_NAME = "firstname";
	private static final String LAST_NAME = "lastname";
	private static final String CONTACT_NO = "contactno";
	private static final String EMAIL_ID = "emailid";
	private static final String USER_NAME = "username";
	
	
	@RequestMapping(value=MusicStoreConstants.LOAD_PROFILE, method=RequestMethod.GET)
	private ModelAndView loadProfileDetails(HttpServletRequest request, HttpSession session) {
		logger.info("\n\n");
		logger.info("Inside loadProfileDetails() :::: ProfileController");
		logger.info("Load profile details to Music Store");
		ModelAndView mv = new ModelAndView();
		try {
			boolean isValidLogin = MusicStoreUtils.authenticateLogin(request);
			String view = (session != null && isValidLogin) ? MusicStoreConstants.MS_PROFILE_PORTLET : MusicStoreConstants.MS_REDIRECT_TO_HOME;
			mv.setViewName(view);
			
			if(isValidLogin) {
				JSONObject userDetails = CommonDao.getMsUserDetails(request);
				int userid = Integer.parseInt(userDetails.getString("user_id"));
				
				JSONObject userDetailsNew = CommonDao.getMsUserDetails(userid);
				mv.addObject("userDetails", userDetailsNew);
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in loadProfileDetails() :::: ProfileController : "+e +"\n");
		}
		return mv;
	}
	
	
	@RequestMapping(value=MusicStoreConstants.UPDATE_PROFILE, method=RequestMethod.POST)
	@ResponseBody
	private String updateProfileDetails(HttpServletRequest request, HttpSession session) {
		logger.info("\n\n");
		logger.info("Inside updateProfileDetails() :::: ProfileController");
		logger.info("Update profile details to Music Store");
		String updateStatus = "N";
		try {
			JSONObject userDetails = CommonDao.getMsUserDetails(request);
			int userid = Integer.parseInt(userDetails.getString("user_id"));
			
			Map<String, String[]> mapRequestParams = request.getParameterMap();
			String firstName = null, lastName = null, contactNo = null, emailId = null, userName = null;
			
			if(mapRequestParams.containsKey(FIRST_NAME) && mapRequestParams.get(FIRST_NAME)[0] != null) {
				firstName = mapRequestParams.get(FIRST_NAME)[0].trim();
			}
			if(mapRequestParams.containsKey(LAST_NAME) && mapRequestParams.get(LAST_NAME)[0] != null) {
				lastName = mapRequestParams.get(LAST_NAME)[0].trim();
			}
			if(mapRequestParams.containsKey(CONTACT_NO) && mapRequestParams.get(CONTACT_NO)[0] != null) {
				contactNo = mapRequestParams.get(CONTACT_NO)[0].trim();
			}
			if(mapRequestParams.containsKey(EMAIL_ID) && mapRequestParams.get(EMAIL_ID)[0] != null) {
				emailId = mapRequestParams.get(EMAIL_ID)[0].trim();
			}
			if(mapRequestParams.containsKey(USER_NAME) && mapRequestParams.get(USER_NAME)[0] != null) {
				userName = mapRequestParams.get(USER_NAME)[0].trim();
			}
			
			if(firstName != null && lastName != null && contactNo != null && emailId != null && userName != null) {
				updateStatus = profileDao.updateProfileDetails(firstName, lastName, Long.parseLong(contactNo), emailId, userName, userid) ? "Y" : "N";
				logger.info("Is Profile details updated : "+updateStatus);
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in updateProfileDetails() :::: ProfileController : "+e +"\n");
		}
		return updateStatus;
	}
}
