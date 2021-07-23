package com.musicstore.dao;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.constants.MusicStoreSQLConstants;
import com.musicstore.utils.AESEncryptDecrypt;

public class LoginLogoutDao {
	private static final Logger logger = Logger.getLogger(LoginLogoutDao.class);
	
	private BaseDao baseDao;
	
	public LoginLogoutDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	
	/** Validate User Login credentials
	 * @param username
	 * @param password
	 * @return
	 */
	public JSONObject validateUserLoginCredentials(String username, String password) {
		logger.info("Inside validateLoginCredentials :::: LoginLogoutDao - username : "+username+", password : "+password);
		JSONObject userDetails = new JSONObject();
		
		try {
			// Check if user exists
			userDetails = (JSONObject) baseDao.selectRecords(MusicStoreSQLConstants.CHECK_USER_EXISTS, new Object[] {username}, MusicStoreConstants.RESULTSET_TYPE_JSON_OBJECT);
			logger.info("User details : "+userDetails);
			
			if(userDetails != null && userDetails.length() > 0) {
				int userid = userDetails.getInt("user_id");
				String passwordFrmDb = userDetails.has("password") ? userDetails.getString("password") : null;
				String passwordEnc = AESEncryptDecrypt.encrypt(MusicStoreConstants.MS_AES_KEY, password);
				
				userDetails = new JSONObject();
				
				if(passwordEnc != null && passwordFrmDb != null && passwordEnc.equals(passwordFrmDb)) {
					userDetails = CommonDao.getMsUserDetails(userid); // Fetch User details
					
					if(userDetails != null && userDetails.length() > 0) {
						userDetails = insertLoginTime(userDetails, username); // Create login entry
					}
				}
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in validateUserLoginCredentials() :::: LoginLogoutDao : "+e +"\n");
		}
		
		logger.info("Returning from validateUserLoginCredentials() :::: LoginLogoutDao ");
		return userDetails;
	}
	
	
	/** Insert login time
	 * @param userDetails
	 * @param username
	 * @return
	 */
	public JSONObject insertLoginTime(JSONObject userDetails, String username) {
		logger.info("Inside insertLoginTime() :::: LoginLogoutDao - username : "+username);
		try {
			int userId = userDetails.has("user_id") ? Integer.parseInt(userDetails.getString("user_id")) : 0;
			String userName = userDetails.has("user_name") ? userDetails.getString("user_name") : null;
			
			// Get last logged in time
			String lastLoginTime = (String) baseDao.selectRecords(MusicStoreSQLConstants.GET_LAST_LOGGED_IN_TIME, new Object[] {userId, userName}, MusicStoreConstants.RESULTSET_TYPE_STRING);
			logger.info("last login time : "+lastLoginTime);
			
			if(lastLoginTime != null && lastLoginTime.length() > 0) {
				userDetails.put("last_login_time", lastLoginTime);
			}
			
			// Insert new login transaction entry
			int count = baseDao.insertRecords(MusicStoreSQLConstants.INSERT_LOGIN_DETAILS, new Object[] {userId, userName, MusicStoreConstants.MS_SYSTEM});
			
			if(count > 0) {
				logger.info("Success. Current login time is created");
			}else {
				logger.info("Failure. Current login time could not be created");
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in insertLoginTime() :::: LoginLogoutDao : "+e +"\n");
		}
		
		return userDetails;
	}
	
	
	/** Update logout time
	 * @param userDetails
	 * @param username
	 */
	public void updateLogoutTime(JSONObject userDetails) {
		logger.info("Inside updateLogoutTime() :::: LoginLogoutDao : ");
		try {
			if(userDetails != null) {
				String userId = userDetails.has("user_id") ? userDetails.getString("user_id") : null;
				String userName = userDetails.has("user_name") ? userDetails.getString("user_name") : null;
				
				if(userId != null && userId.length() > 0 && userName != null && userName.length() > 0) {
					int updateCount = baseDao.updateRecords(MusicStoreSQLConstants.UPDATE_LOGOUT_DETAILS, new Object[] {MusicStoreConstants.MS_SYSTEM, Integer.parseInt(userId), userName});
					
					if(updateCount > 0) {
						logger.info("Success. Current logout time is updated");
					}else {
						logger.info("Failure. Current logout time could not be updated");
					}
				}
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in updateLogoutTime() :::: LoginLogoutDao : "+e +"\n");
		}
		
	}

}

