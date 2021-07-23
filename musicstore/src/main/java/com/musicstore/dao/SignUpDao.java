package com.musicstore.dao;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.constants.MusicStoreSQLConstants;
import com.musicstore.utils.AESEncryptDecrypt;

public class SignUpDao {
	private static final Logger logger = Logger.getLogger(SignUpDao.class);
	
	private BaseDao baseDao;
	
	public SignUpDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	
	/** Check user-name availability
	 */
	public String checkUserNameAvailability(String userName) {
		int count = (Integer) baseDao.selectRecords(MusicStoreSQLConstants.CHECK_USER_NAME_AVAILABILITY, new Object[] {userName}, MusicStoreConstants.RESULTSET_TYPE_INTEGER);
		if(count == 0)
			return "Y";
		else
			return "N";
	}
	
	
	/** Add new user details : MS_USER_DETAILS
	 */
	public JSONObject addNewUserDetails(JSONObject newUserDetails) {
		logger.info("Inside addNewUserDetails() :::: MusicStoreDao : "+newUserDetails);
		JSONObject addNewUserDetails = new JSONObject();
		try {
			if(newUserDetails != null && newUserDetails.length() > 0) {
				String firstName = newUserDetails.has("firstname") ? newUserDetails.getString("firstname") : null;
				String lastName = newUserDetails.has("lastname") ? newUserDetails.getString("lastname") : null;
				int contactNo = newUserDetails.has("contactno") ? Integer.parseInt(newUserDetails.getString("contactno")) : null;
				String emailId = newUserDetails.has("emailid") ? newUserDetails.getString("emailid") : null;
				String userName = newUserDetails.has("username") ? newUserDetails.getString("username") : null;
				String password = newUserDetails.has("password") ? newUserDetails.getString("password") : null;
				String confirmPassword = newUserDetails.has("confirmpassword") ? newUserDetails.getString("confirmpassword") : null;
				
				/* Validate form again */
				boolean isNewUserFormValid = validateAddNewUserForm(firstName, lastName, contactNo, emailId, userName, password, confirmPassword);
				logger.info("Is new user form valid : "+isNewUserFormValid);
				
				if(isNewUserFormValid){
					/* Encrypt password */
					String passwordEnc = AESEncryptDecrypt.encrypt(MusicStoreConstants.MS_AES_KEY, password);
					Object params[] = new Object[] {firstName, lastName, contactNo, emailId, userName, passwordEnc, MusicStoreConstants.MS_ACTIVE_USER, MusicStoreConstants.MS_ROLE_USER, MusicStoreConstants.MS_SYSTEM};
					int insertCount = baseDao.insertRecords(MusicStoreSQLConstants.INSERT_NEW_MS_USER_DETAILS, params);
					
					addNewUserDetails.put("isNewUserFormValid", (isNewUserFormValid) ? "Y" : "N");
					addNewUserDetails.put("isNewUserAdded", (insertCount > 0) ? "Y" : "N");
					addNewUserDetails.put("username", userName);
				}
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in addNewUserDetails() :::: MusicStoreDao : "+e);
		}
		logger.info("Add new user details : "+addNewUserDetails);
		return addNewUserDetails;
	}
	
	
	/** Validate Add new user form 
	 * @param firstName
	 * @param lastName
	 * @param contactNo
	 * @param emailId
	 * @param userName
	 * @param password
	 * @param confirmPassword
	 * @return
	 */
	public boolean validateAddNewUserForm(String firstName, String lastName, int contactNo, String emailId, String userName, 
			String password, String confirmPassword) {
		boolean isNewUserFormValid = false;
		if(firstName != null && lastName != null && contactNo != 0 && emailId != null && userName != null && 
				password != null && confirmPassword != null) {
			if(Pattern.matches(MusicStoreConstants.VALID_NAME_REGEX, firstName) && Pattern.matches(MusicStoreConstants.VALID_NAME_REGEX, lastName) &&
					Pattern.matches(MusicStoreConstants.VALID_CONTACT_NO, String.valueOf(contactNo)) && Pattern.matches(MusicStoreConstants.VALID_EMAIL_REGEX, emailId) && 
						Pattern.matches(MusicStoreConstants.VALID_USERNAME_REGEX, userName) && password.equals(confirmPassword)) {
				isNewUserFormValid = true;
			}
		}
		return isNewUserFormValid;
	}
	
}
