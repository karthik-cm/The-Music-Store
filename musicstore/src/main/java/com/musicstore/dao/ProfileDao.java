package com.musicstore.dao;

import org.apache.log4j.Logger;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.constants.MusicStoreSQLConstants;

public class ProfileDao {
	private static final Logger logger = Logger.getLogger(ProfileDao.class);
	
	private BaseDao baseDao;
	
	public ProfileDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	
	public boolean updateProfileDetails(String firstName, String lastName, long contactNo, String emailId, String userName, int userid) {
		logger.info("Inside updateProfileDetails() :::: ProfileDao - userid : "+userid);
		boolean status = false;
		try {
			Object params[] = {firstName, lastName, contactNo, emailId, userName, MusicStoreConstants.MS_SYSTEM, userid};
			int updateCount = baseDao.updateRecords(MusicStoreSQLConstants.UPDATE_PROFILE_DETAILS, params);
			if(updateCount > 0) {
				status = true;
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in updateProfileDetails() :::: ProfileDao : "+e +"\n");
		}
		return status;
	}
}
