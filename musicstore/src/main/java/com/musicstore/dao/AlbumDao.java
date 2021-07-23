package com.musicstore.dao;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.constants.MusicStoreSQLConstants;

public class AlbumDao {
	private static final Logger logger = Logger.getLogger(AlbumDao.class);
	
	private BaseDao baseDao;
	
	public AlbumDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	
	/** To insert Album details to DB 
	 * @param nameOfAlbum
	 * @param genre
	 * @param musicBy
	 * @param yearOfRelease
	 * @param userId
	 * @param userName
	 * @return
	 */
	public boolean insertAlbumDetails(String nameOfAlbum, String genre, String musicBy, int yearOfRelease, int userId, String userName, String activeFlag) {
		logger.info("Inside insertAlbumDetails() :::: AlbumDao - userName : "+userName);
		int count = 0;
		try {
			Object params[] = {nameOfAlbum, genre, musicBy, yearOfRelease, userId, userName, String.valueOf(userId), activeFlag};
			count = baseDao.insertRecords(MusicStoreSQLConstants.INSERT_NEW_ALBUM_DETAILS, params);
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in insertAlbumDetails() :::: AlbumDao : "+e +"\n");
		}
		
		if(count > 0)
			return true;
		else 
			return false;
	}
	
	
	/** To get Album details from DB
	 * @param userId
	 * @param userName
	 * @return
	 */
	public JSONArray getAlbumDetails (int userId, String userName) {
		logger.info("Inside getAlbumDetails() :::: AlbumDao - userName : "+userName);
		JSONArray albumDetails = new JSONArray();
		try {
			albumDetails = (JSONArray) baseDao.selectRecords(MusicStoreSQLConstants.GET_ALBUM_DETAILS, new Object[] {userId, userName}, MusicStoreConstants.RESULTSET_TYPE_JSON_ARRAY);
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in getAlbumDetails() :::: AlbumDao : "+e +"\n");
		}
		return albumDetails;
	}
	
	
	/** To update Album details 
	 * @param nameOfAlbum
	 * @param genre
	 * @param musicBy
	 * @param yearOfRelease
	 * @param albumId
	 * @param userId
	 * @param userName
	 * @return
	 */
	public boolean updateAlbumDetails(String nameOfAlbum, String genre, String musicBy, int yearOfRelease, int albumId, int userId, String userName) {
		logger.info("\nInside updateAlbumDetails() :::: AlbumDao >> userName : "+userName);
		int count = 0;
		try {
			Object params[] = {nameOfAlbum, genre, musicBy, yearOfRelease, String.valueOf(userId), albumId, userId, userName};
			count = baseDao.updateRecords(MusicStoreSQLConstants.UPDATE_ALBUM_DETAILS, params);
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in updateAlbumDetails() :::: AlbumDao : "+e +"\n");
		}
		
		if(count > 0)
			return true;
		else 
			return false;
	}
	
	
	/** To delete given album
	 * @param albumId
	 * @param userId
	 * @param userName
	 * @return
	 */
	public boolean deleteAlbumDetails(int albumId, int userId, String userName) {
		int count = 0;
		try {
			Object params[] = {albumId, userId, userName};
			int albumCount = (Integer) baseDao.selectRecords(MusicStoreSQLConstants.CHECK_ALBUM_BELONGS_TO_USER, params, MusicStoreConstants.RESULTSET_TYPE_INTEGER);
			
			if(albumCount > 0) {
				count = baseDao.updateRecords(MusicStoreSQLConstants.DELETE_ALBUM_DETAILS, params);
				if(count > 0) {
					logger.info("Success.. Deleted album for user name : "+userName +" with album id : "+albumId);
				}else {
					logger.info("Error.. Could not delete album for user name : "+userName +" with album id : "+albumId);
				}
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in deleteAlbumDetails() :::: AlbumDao : "+e +"\n");
		}
		
		if(count > 0)
			return true;
		else
			return false;
	}
}
