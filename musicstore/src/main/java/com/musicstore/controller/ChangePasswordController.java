package com.musicstore.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.dao.ChangePasswordDao;
import com.musicstore.dao.CommonDao;
import com.musicstore.utils.AESEncryptDecrypt;
import com.musicstore.utils.MusicStoreUtils;

@Controller
public class ChangePasswordController {
	private static final Logger logger = Logger.getLogger(ChangePasswordController.class);
	
	private ChangePasswordDao changePasswordDao;
	
	public ChangePasswordDao getChangePasswordDao() {
		return changePasswordDao;
	}

	public void setChangePasswordDao(ChangePasswordDao changePasswordDao) {
		this.changePasswordDao = changePasswordDao;
	}

	
	private static final String OLD_PASSWORD = "oldpwd";
	private static final String NEW_PASSWORD = "newpwd";
	private static final String CONFIRM_PASSWORD = "conpwd";

	@RequestMapping(value=MusicStoreConstants.LOAD_CHANGE_PWD, method=RequestMethod.GET)
	public ModelAndView loadChangePwd(HttpServletRequest request) {
		logger.info("\n\n");
		logger.info("Inside loadChangePwd() :::: ChangePasswordController");
		logger.info("Load change password portlet of Music Store");
		
		ModelAndView mv = new ModelAndView();
		try {
			boolean isValidLogin = MusicStoreUtils.authenticateLogin(request);
			if(isValidLogin) {
				mv.setViewName(MusicStoreConstants.MS_CHANGE_PWD_PORTLET);
			}else {
				mv.setViewName(MusicStoreConstants.MS_REDIRECT_TO_HOME);
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in loadChangePwd() :::: ChangePasswordController : "+e +"\n");
		}
		return mv;
	}
	
	@RequestMapping(value=MusicStoreConstants.CHANGE_PWD, method=RequestMethod.POST)
	@ResponseBody
	public String changePassword(HttpServletRequest request) {
		JSONObject response = new JSONObject();
		String status = "N";
		String respMsg = null; 
		
		logger.info("\n\n");
		logger.info("Inside changePassword() :::: ChangePasswordController");
		logger.info("Change password of Music Store");
		
		try {
			Map<String,String[]> mapRequestParams = request.getParameterMap();
			String oldPwd = null, newPwd = null, conPwd = null;
			
			if(mapRequestParams.containsKey(OLD_PASSWORD) && mapRequestParams.get(OLD_PASSWORD)[0] != null) {
				oldPwd = mapRequestParams.get(OLD_PASSWORD)[0].trim();
			}
			if(mapRequestParams.containsKey(NEW_PASSWORD) && mapRequestParams.get(NEW_PASSWORD)[0] != null) {
				newPwd = mapRequestParams.get(NEW_PASSWORD)[0].trim();
			}
			if(mapRequestParams.containsKey(CONFIRM_PASSWORD) && mapRequestParams.get(CONFIRM_PASSWORD)[0] != null) {
				conPwd = mapRequestParams.get(CONFIRM_PASSWORD)[0].trim();
			}
			
			if(oldPwd != null && oldPwd.length() > 0 && newPwd != null && newPwd.length() > 0 && conPwd != null && conPwd.length() > 0) {
				JSONObject userDetails = CommonDao.getMsUserDetails(request);
				int userid = userDetails.getInt("user_id");
				String username = userDetails.getString("user_name");
				
				// Verify existing password
				boolean isOldPwdValid = CommonDao.validatePassword(username, oldPwd);
				
				if(isOldPwdValid) {
					if(newPwd.length() == conPwd.length() && newPwd.equals(conPwd)) {
						
						// Old pwd != New pwd
						if(!oldPwd.equals(newPwd)) {
							String password = AESEncryptDecrypt.encrypt(MusicStoreConstants.MS_AES_KEY, newPwd);
							boolean isPwdChanged = changePasswordDao.changePwdDetails(password, userid, username);
							if(isPwdChanged) {
								status = "Y"; respMsg = "The password has been updated successfully";
							}
						}else{
							respMsg = "Old password and New password cannot be same. Please check.";
						}
					}else{
						respMsg = "New password and Confirm password do not match. Please check.";
					}
				}else{
					respMsg = "Old password entered is incorrect. Please check.";
				}
			}else {
				respMsg = "Fields marked with (*) cannot be left blank";
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in changePassword() :::: ChangePasswordController : "+e +"\n");
		}
		response.put("status", status);
		response.put("respMsg", respMsg);
		logger.debug("Final response from change password : "+response);
		return response.toString();
	}
}
