package com.musicstore.dao;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.constants.MusicStoreSQLConstants;

public class TrackDao {
	private static final Logger logger = Logger.getLogger(TrackDao.class);
	
	private BaseDao baseDao;

	public TrackDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	
	public boolean insertTrackDetails(int albumId, String trackName, String artists, String duration, int sequenceNo, int userId, String userName) {
		logger.info("Inside insertTrackDetails() :::: TrackDao "); 
		try {
			Object params[] = {albumId, trackName, artists, duration, sequenceNo, userName, MusicStoreConstants.MS_ACTIVE_FLAG_Y};
			int insertCount = baseDao.insertRecords(MusicStoreSQLConstants.INSERT_NEW_TRACK_DETAILS, params);
			if(insertCount > 0) {
				return true;
			}else {
				return false;
			}
		}
		catch (Exception e) {
			logger.error("\n\nException occurred in insertTrackDetails() :::: TrackDao : "+e +"\n");
		}
		return false;
	}
	
	
	public JSONArray getAlbumTrackDetails(int userId) {
		logger.info("Inside getTrackDetails() :::: TrackDao - userId : "+userId);
		JSONArray trackDetails = null;
		try {
			trackDetails = (JSONArray) baseDao.selectRecords(MusicStoreSQLConstants.GET_TRACK_DETALS, new Object[] {userId}, MusicStoreConstants.RESULTSET_TYPE_JSON_ARRAY);
			logger.debug("Track details : "+trackDetails);
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in getTrackDetails() :::: TrackDao : "+e +"\n");
		}
		
		return trackDetails;
	}
	
	
	public JSONObject formAlbumTrackStructure(JSONArray albumTrackDtlsDb) {
		logger.info("Inside formAlbumTrackStructure() :::: TrackDao ");
		Set<Integer> albumIdSet = new HashSet<Integer>();
		JSONObject albumTrackDtlsObj = new JSONObject();
		JSONArray albumTrackDtlsArr = new JSONArray();
		int trackIndex = 0;
		try {
			JSONObject currAlbumTrackDtlsObj = new JSONObject();
			for(int i=0; i<albumTrackDtlsDb.length(); i++) {
				JSONObject albumTrackDtlsDbObj = albumTrackDtlsDb.getJSONObject(i);
				int albumId = albumTrackDtlsDbObj.getInt("album_id");
				String albumName = albumTrackDtlsDbObj.getString("album_name");
				int tracksCount = albumTrackDtlsDbObj.getInt("track_count");
				boolean albumIdExists = albumIdSet.contains(albumId);
				
				if(!albumIdExists) {
					albumIdSet.add(albumId);
					currAlbumTrackDtlsObj = new JSONObject();
					currAlbumTrackDtlsObj.put("albumid", albumId);
					currAlbumTrackDtlsObj.put("albumName", albumName);
					currAlbumTrackDtlsObj.put("tracksCount", tracksCount);
					currAlbumTrackDtlsObj.put("tracks", new JSONArray());
					albumTrackDtlsArr.put(currAlbumTrackDtlsObj);
					trackIndex = 0;
				}
				
				if(tracksCount > 0){
					trackIndex++;
					int trackId = albumTrackDtlsDbObj.getInt("track_id");
					String trackName = albumTrackDtlsDbObj.getString("track_name");
					String artists = albumTrackDtlsDbObj.getString("artists");
					String duration = albumTrackDtlsDbObj.getString("duration");
					int sequenceNo = albumTrackDtlsDbObj.getInt("sequence_no");
					String createdDt = albumTrackDtlsDbObj.getString("created_dt");
					
					JSONObject trackDetailsObj = new JSONObject();
					trackDetailsObj.put("trackIndex", trackIndex);
					trackDetailsObj.put("trackId", trackId);
					trackDetailsObj.put("trackName", trackName);
					trackDetailsObj.put("artists", artists);
					trackDetailsObj.put("duration", duration);
					trackDetailsObj.put("sequenceNo", sequenceNo);
					trackDetailsObj.put("createdDt", createdDt);
					
					currAlbumTrackDtlsObj.getJSONArray("tracks").put(trackDetailsObj);
				}
			}
			albumTrackDtlsObj.put("albumTrackDtls", albumTrackDtlsArr);
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in formAlbumTrackStructure() :::: TrackDao : "+e +"\n");
		}
		return albumTrackDtlsObj;
	}


	public boolean updateTrackDetails(int albumId, int trackId, String trackName, String artists, String duration, int sequenceNo, int userId, String userName) {
		logger.info("Inside updateTrackDetails() :::: TrackDao ");
		try {
			Object params[] = new Object[] {albumId, trackName, artists, duration, sequenceNo, trackId, userId};
			int updateCount = baseDao.updateRecords(MusicStoreSQLConstants.UPDATE_TRACK_DETAILS, params);
			if(updateCount > 0) {
				return true;
			}else {
				return false;
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in updateTrackDetails() :::: TrackDao : "+e +"\n");
		}
		return false;
	}


	public boolean deleteTrackDetails(int trackId, int userId, String userName) {
		logger.info("Inside updateTrackDetails() :::: TrackDao ");
		try {
			Object params[] = new Object[] {trackId, userName};
			int updateCount = baseDao.updateRecords(MusicStoreSQLConstants.DELETE_TRACK_DETAILS, params);
			if(updateCount > 0) {
				return true;
			}else {
				return false;
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in deleteTrackDetails() :::: TrackDao : "+e +"\n");
		}
		return false;
	}
}
