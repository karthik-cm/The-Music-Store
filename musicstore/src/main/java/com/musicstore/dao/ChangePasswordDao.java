package com.musicstore.dao;

import org.apache.log4j.Logger;

import com.musicstore.constants.MusicStoreSQLConstants;

public class ChangePasswordDao {
	private static final Logger logger = Logger.getLogger(ChangePasswordDao.class);
	
	private BaseDao baseDao;
	
	public ChangePasswordDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	
	
	public boolean changePwdDetails(String password, int userid, String username) {
		logger.info("Inside changePwdDetails() :::: ChangePasswordDao - userid : "+userid +", username : "+username);
		boolean isPwdChanged = false;
		try {
			Object params[] = {password, username, userid, username};
			int updateCount = baseDao.updateRecords(MusicStoreSQLConstants.UPDATE_PASSWORD_DETAILS, params);
			if(updateCount > 0) {
				isPwdChanged = true;
			}
			logger.info("Is password changed : "+isPwdChanged);
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in changePwdDetails() :::: ChangePasswordDao : "+e +"\n");
		}
		return isPwdChanged;
	}
}
