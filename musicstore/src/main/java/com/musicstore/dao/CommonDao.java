package com.musicstore.dao;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.constants.MusicStoreSQLConstants;
import com.musicstore.utils.AESEncryptDecrypt;
import com.musicstore.utils.SessionUtil;

public class CommonDao {
	
	private static final Logger logger = Logger.getLogger(CommonDao.class);
	
	private static BaseDao baseDao;
	
	public CommonDao(BaseDao baseDao) {
		CommonDao.baseDao = baseDao;
	}
	
	
	public static boolean validatePassword(String username, String password) {
		logger.info("Inside validateLoginCredentials :::: LoginLogoutDao - username : "+username+", password : "+password);
		boolean isPwdValid = false;
		
		try {
			// Check if user exists
			JSONObject userDetails = (JSONObject) baseDao.selectRecords(MusicStoreSQLConstants.CHECK_USER_EXISTS, new Object[] {username}, MusicStoreConstants.RESULTSET_TYPE_JSON_OBJECT);
			
			if(userDetails != null && userDetails.length() > 0) {
				String passwordFrmDb = userDetails.has("password") ? userDetails.getString("password") : null;
				String passwordEnc = AESEncryptDecrypt.encrypt(MusicStoreConstants.MS_AES_KEY, password);
				
				if(passwordEnc != null && passwordFrmDb != null && passwordEnc.equals(passwordFrmDb)) {
					isPwdValid = true;
				}
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in validatePassword() :::: CommonDao : "+e);
		}
		return isPwdValid;
	}
	
	
	/** Get music store user details
	 * @param username
	 * @return
	 */
	public static JSONObject getMsUserDetails(HttpServletRequest request) {
		logger.info("Inside getMsUserDetails() :::: CommonDao");
		JSONObject userDetails = null;
		try {
			userDetails = (JSONObject) SessionUtil.getAttribute(request, MusicStoreConstants.MS_USER_DETAILS);
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in getMsUserDetails() :::: CommonDao : "+e);
		}
		logger.info("User details : "+userDetails);
		return userDetails;
	}
	
	
	/** Get music store user details
	 * @param userid
	 * @return
	 */
	public static JSONObject getMsUserDetails(int userid) {
		logger.info("Inside getMsUserDetails() :::: CommonDao - userid : "+userid);
		JSONObject userDetails = null;
		try {
			userDetails = (JSONObject) baseDao.selectRecords(MusicStoreSQLConstants.GET_MS_USER_DETAILS_ID, new Object[] {userid}, MusicStoreConstants.RESULTSET_TYPE_JSON_OBJECT);
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in getMsUserDetails() :::: CommonDao : "+e);
		}
		logger.info("User details : "+userDetails);
		return userDetails;
	}
	
	
	/** Get music store user details
	 * @param userid
	 * @return
	 */
	public static JSONObject getMsUserDetails(String userName) {
		logger.info("Inside getMsUserDetails() :::: CommonDao - userName : "+userName);
		JSONObject userDetails = null;
		try {
			userDetails = (JSONObject) baseDao.selectRecords(MusicStoreSQLConstants.GET_MS_USER_DETAILS_NAME, new Object[] {userName}, MusicStoreConstants.RESULTSET_TYPE_JSON_OBJECT);
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in getMsUserDetails() :::: CommonDao : "+e);
		}
		logger.info("User details : "+userDetails);
		return userDetails;
	}
}
