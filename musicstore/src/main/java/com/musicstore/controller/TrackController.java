package com.musicstore.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.dao.TrackDao;
import com.musicstore.utils.MusicStoreUtils;
import com.musicstore.utils.SessionUtil;

@Controller
public class TrackController {
	private static final Logger logger = Logger.getLogger(TrackController.class);
	
	private TrackDao trackDao;
	
	public TrackDao getTrackDao() {
		return trackDao;
	}

	public void setTrackDao(TrackDao trackDao) {
		this.trackDao = trackDao;
	}

	
	private static final String ALBUM_ID = "albumid";
	private static final String TRACK_ID = "trackid";
	private static final String TRACK_NAME = "trackname";
	private static final String ARTISTS = "artists";
	private static final String DURATION = "duration";
	private static final String SEQUENCE_NO = "sequenceno";
	
	
	@RequestMapping(value=MusicStoreConstants.LOAD_TRACK, method=RequestMethod.GET)
	private ModelAndView loadTrack(HttpServletRequest request, HttpSession session) {
		logger.info("\n\n");
		logger.info("Inside loadTrack() :::: TrackController");
		logger.info("Load Track to Music Store");
		ModelAndView mv = new ModelAndView();
		
		try {
			boolean isValidLogin = MusicStoreUtils.authenticateLogin(request);
			String view = (session != null && isValidLogin) ? MusicStoreConstants.MS_TRACK_PORTLET : MusicStoreConstants.MS_REDIRECT_TO_HOME;
			mv.setViewName(view);
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in loadTrack() :::: TrackController : "+e +"\n");
		}
		return mv;
	}
	
	
	/* Add Track to Album in Music Store
	 * 
	 */
	@RequestMapping(value=MusicStoreConstants.ADD_TRACK, method=RequestMethod.POST)
	@ResponseBody
	public String addTrack(HttpServletRequest request, HttpSession session) {
		String status = "N";
		logger.info("\n\n");
		logger.info("Inside addTrack() :::: TrackController");
		logger.info("Add Track to Album : Music Store");
		
		try {
			if(session != null) {
				JSONObject userDetails = (JSONObject) SessionUtil.getAttribute(request, MusicStoreConstants.MS_USER_DETAILS);
				int userId = Integer.parseInt(userDetails.getString("user_id"));
				String userName = userDetails.getString("user_name");
				
				Map<String, String[]> requestParamsMap = request.getParameterMap();
				String albumId = null, trackName = null, artists = null, duration = null, sequenceNo = null;
				
				if(requestParamsMap.containsKey(ALBUM_ID)) {
					albumId = requestParamsMap.get(ALBUM_ID)[0] != null ? requestParamsMap.get(ALBUM_ID)[0].trim() : null;
				}
				if(requestParamsMap.containsKey(TRACK_NAME)) {
					trackName = requestParamsMap.get(TRACK_NAME)[0] != null ? requestParamsMap.get(TRACK_NAME)[0].trim() : null;
				}
				if(requestParamsMap.containsKey(ARTISTS)) {
					artists = requestParamsMap.get(ARTISTS)[0] != null ? requestParamsMap.get(ARTISTS)[0].trim() : null;
				}
				if(requestParamsMap.containsKey(DURATION)) {
					duration = requestParamsMap.get(DURATION)[0] != null ? requestParamsMap.get(DURATION)[0].trim() : null;
				}
				if(requestParamsMap.containsKey(SEQUENCE_NO)) {
					sequenceNo = requestParamsMap.get(SEQUENCE_NO)[0] != null ? requestParamsMap.get(SEQUENCE_NO)[0].trim() : null;
				}
				
				// Insert track
				boolean isNewAlbumInserted = trackDao.insertTrackDetails(Integer.parseInt(albumId), trackName, artists, duration, Integer.parseInt(sequenceNo), userId, userName);
				
				if(isNewAlbumInserted) {
					status = "Y";
				}
				logger.info("New track insert status : "+isNewAlbumInserted +", status : "+status);
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in addTrack() :::: TrackController : "+e +"\n");
		}
		return status;
	}
	
	
	/* View Track to Album in Music Store
	 * 
	 */
	@RequestMapping(value=MusicStoreConstants.VIEW_TRACK, method=RequestMethod.GET)
	@ResponseBody
	public String viewTrack(HttpServletRequest request) {
		logger.info("\n\n");
		logger.info("Inside viewTrack() :::: TrackController");
		logger.info("View Track of Album : Music Store");
		String albumTrackDtls = null;
		try {
			JSONObject userDetails = (JSONObject) SessionUtil.getAttribute(request, MusicStoreConstants.MS_USER_DETAILS);
			int userId = Integer.parseInt(userDetails.getString("user_id"));
			
			JSONArray albumTrackDtlsArr = trackDao.getAlbumTrackDetails(userId);
			if(albumTrackDtlsArr != null && albumTrackDtlsArr.length() > 0) {
				albumTrackDtls = trackDao.formAlbumTrackStructure(albumTrackDtlsArr).toString();
				logger.info("Final view track details : "+albumTrackDtls);
			}else {
				albumTrackDtls = "";
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in viewTrack() :::: TrackController : "+e +"\n");
		}
		return albumTrackDtls;
	}
	
	
	/** Update Track
	 */
	@RequestMapping(value=MusicStoreConstants.UPDATE_TRACK, method=RequestMethod.POST)
	@ResponseBody
	private String updateTrack(HttpServletRequest request, HttpSession session) {
		String status = "N";
		logger.info("\n\n");
		logger.info("Inside updateTrack() :::: TrackController :::: ");
		logger.info("Update Track details");
		
		try {
			if(session != null) {
				JSONObject userDetails = (JSONObject) session.getAttribute(MusicStoreConstants.MS_USER_DETAILS);
				int userId = Integer.parseInt(userDetails.getString("user_id"));
				String userName = userDetails.getString("user_name");
				
				Map<String, String[]> requestParamsMap = request.getParameterMap();
				String albumId = null, trackId = null, trackName = null, artists = null, duration = null, sequenceNo = null;
				
				if(requestParamsMap.containsKey(ALBUM_ID)) {
					albumId = requestParamsMap.get(ALBUM_ID)[0] != null ? requestParamsMap.get(ALBUM_ID)[0].trim() : null;
				}
				if(requestParamsMap.containsKey(TRACK_ID)) {
					trackId = requestParamsMap.get(TRACK_ID)[0] != null ? requestParamsMap.get(TRACK_ID)[0].trim() : null;
				}
				if(requestParamsMap.containsKey(TRACK_NAME)) {
					trackName = requestParamsMap.get(TRACK_NAME)[0] != null ? requestParamsMap.get(TRACK_NAME)[0].trim() : null;
				}
				if(requestParamsMap.containsKey(ARTISTS)) {
					artists = requestParamsMap.get(ARTISTS)[0] != null ? requestParamsMap.get(ARTISTS)[0].trim() : null;
				}
				if(requestParamsMap.containsKey(DURATION)) {
					duration = requestParamsMap.get(DURATION)[0] != null ? requestParamsMap.get(DURATION)[0].trim() : null;
				}
				if(requestParamsMap.containsKey(SEQUENCE_NO)) {
					sequenceNo = requestParamsMap.get(SEQUENCE_NO)[0] != null ? requestParamsMap.get(SEQUENCE_NO)[0].trim() : null;
				}
				
				logger.info("Details entered : Update Track >> albumId : "+albumId +", trackId : "+trackId +", trackName : "+trackName +", artists : "+artists +", duration : "+duration +", sequenceNo : "+sequenceNo);
				
				// Update Album
				boolean isTrackUpdated = trackDao.updateTrackDetails(Integer.parseInt(albumId), Integer.parseInt(trackId), trackName, artists, duration, Integer.parseInt(sequenceNo), userId, userName);
				
				if(isTrackUpdated) {
					status = "Y";
				}
				logger.info("Is track updated : "+isTrackUpdated +", status : "+status);
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in updateTrack() :::: TrackController : "+e +"\n");
		}
		
		return status;
	}
	
	
	/* Delete Album
	 * */
	@RequestMapping(value=MusicStoreConstants.DELETE_TRACK, method=RequestMethod.POST)
	@ResponseBody
	private String deleteTrack(HttpServletRequest request, HttpSession session) {
		String status = "N";
		logger.info("\n\n");
		logger.info("Inside deleteTrack() :::: TrackController ");
		logger.info("Delete Track");
		
		try {
			String trackId = request.getParameterMap().containsKey(TRACK_ID) ? request.getParameterMap().get(TRACK_ID)[0].trim() : null;
			
			if(session != null && trackId != null) {
				JSONObject userDetails = (JSONObject) session.getAttribute(MusicStoreConstants.MS_USER_DETAILS);
				int userId = Integer.parseInt(userDetails.getString("user_id"));
				String userName = userDetails.getString("user_name");
				
				boolean isTrackDeleted = trackDao.deleteTrackDetails(Integer.parseInt(trackId), userId, userName);
				
				if(isTrackDeleted) {
					status = "Y";
				}
				logger.info("Is track deleted : "+isTrackDeleted +", status : "+status);
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in deleteTrack() :::: TrackController : "+e +"\n");
		}
		return status;
	}
}
