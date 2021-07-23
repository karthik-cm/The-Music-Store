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
import com.musicstore.dao.AlbumDao;
import com.musicstore.utils.MusicStoreUtils;
import com.musicstore.utils.SessionUtil;

@Controller
public class AlbumController {
	private static Logger logger = Logger.getLogger(AlbumController.class);
	
	private AlbumDao albumDao;
	
	public AlbumDao getAlbumDao() {
		return albumDao;
	}
	public void setAlbumDao(AlbumDao albumDao) {
		this.albumDao = albumDao;
	}
	
	private static final String NAME_OF_ALBUM = "nameofalbum";
	private static final String GENRE = "genre";
	private static final String MUSIC_BY = "musicby";
	private static final String YEAR_OF_RELEASE = "yearofrelease";
	private static final String ALBUM_ID = "albumid";
	
	
	@RequestMapping(value=MusicStoreConstants.LOAD_ALBUM, method=RequestMethod.GET)
	private ModelAndView loadAlbum(HttpServletRequest request, HttpSession session) {
		logger.info("\n\n");
		logger.info("Inside loadAlbum() :::: AlbumController");
		logger.info("Load Album to Music Store");
		ModelAndView mv = new ModelAndView();
		
		try {
			boolean isValidLogin = MusicStoreUtils.authenticateLogin(request);
			String view = (session != null && isValidLogin) ? MusicStoreConstants.MS_ALBUM_PORTLET : MusicStoreConstants.MS_REDIRECT_TO_HOME;
			mv.setViewName(view);
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in loadAlbum() :::: AlbumController : "+e +"\n");
		}
		return mv;
	}
	
	
	/** To add Album
	 */
	@RequestMapping(value=MusicStoreConstants.ADD_ALBUM, method=RequestMethod.POST)
	@ResponseBody
	private String addAlbum(HttpServletRequest request, HttpSession session) {
		String status = "N";
		logger.info("\n\n");
		logger.info("Inside addAlbum() :::: AlbumController");
		logger.info("Add Album to Music Store");
		
		try {
			if(session != null) {
				JSONObject userDetails = (JSONObject) SessionUtil.getAttribute(request, MusicStoreConstants.MS_USER_DETAILS);
				int userId = Integer.parseInt(userDetails.getString("user_id"));
				String userName = userDetails.getString("user_name");
				
				Map<String, String[]> requestParamMap = request.getParameterMap();
				String nameOfAlbum = null, genre = null, musicBy = null, yearOfRelease = null;
				
				if(requestParamMap.containsKey(NAME_OF_ALBUM)) {
					nameOfAlbum = (requestParamMap.get(NAME_OF_ALBUM)[0] != null) ? requestParamMap.get(NAME_OF_ALBUM)[0].trim() : null;
				}
				if(requestParamMap.containsKey(GENRE)) {
					genre = (requestParamMap.get(GENRE)[0] != null) ? requestParamMap.get(GENRE)[0].trim() : null;
				}
				if(requestParamMap.containsKey(MUSIC_BY)) {
					musicBy = (requestParamMap.get(MUSIC_BY)[0] != null) ? requestParamMap.get(MUSIC_BY)[0].trim() : null;
				}
				if(requestParamMap.containsKey(YEAR_OF_RELEASE)) {
					yearOfRelease = (requestParamMap.get(YEAR_OF_RELEASE)[0] != null) ? requestParamMap.get(YEAR_OF_RELEASE)[0].trim() : null;
				}
				
				logger.info("Details entered for Add Album :::: nameOfAlbum : "+nameOfAlbum + ", genre : "+genre +", musicBy : "+musicBy +", yearOfRelease : "+yearOfRelease);
				
				// Insert album
				boolean isNewAlbumInserted = albumDao.insertAlbumDetails(nameOfAlbum, genre, musicBy, Integer.parseInt(yearOfRelease), userId, userName, MusicStoreConstants.MS_ACTIVE_FLAG_Y);
				
				if(isNewAlbumInserted) {
					status = "Y";
				}
				logger.info("New album insert status : "+isNewAlbumInserted +", status : "+status);
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in addAlbum() :::: AlbumController : "+e +"\n");
		}
		return status;
	}
	
	
	/** View Album
	 */
	@RequestMapping(value=MusicStoreConstants.VIEW_ALBUM, method=RequestMethod.GET)
	@ResponseBody
	private String viewAlbum(HttpServletRequest request, HttpSession session) {
		logger.info("\n\n");
		logger.info("Inside viewAlbum() :::: AlbumController");
		logger.info("View Album details");
		JSONArray albumDetails = new JSONArray();
		
		try {
			if(session != null) {
				JSONObject userDetails = (JSONObject) session.getAttribute(MusicStoreConstants.MS_USER_DETAILS);
				int userId = Integer.parseInt(userDetails.getString("user_id"));
				String userName = userDetails.getString("user_name");
				
				// Get Album details
				albumDetails = albumDao.getAlbumDetails(userId, userName);
				logger.info("Album details for userId : "+userId +", userName : "+userName +" is " +albumDetails);
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in viewAlbum() :::: AlbumController : "+e +"\n");
		}
		
		if(albumDetails != null)
			return albumDetails.toString();
		else
			return null;
	}
	
	
	/** Update Album
	 */
	@RequestMapping(value=MusicStoreConstants.UPDATE_ALBUM, method=RequestMethod.POST)
	@ResponseBody
	private String updateAlbum(HttpServletRequest request, HttpSession session) {
		String status = "N";
		logger.info("\n\n");
		logger.info("Inside updateAlbum() :::: AlbumController :::: ");
		logger.info("Update Album details");
		
		try {
			if(session != null) {
				JSONObject userDetails = (JSONObject) session.getAttribute(MusicStoreConstants.MS_USER_DETAILS);
				int userId = Integer.parseInt(userDetails.getString("user_id"));
				String userName = userDetails.getString("user_name");
				
				Map<String, String[]> requestParamMap = request.getParameterMap();
				String nameOfAlbum = null, genre = null, musicBy = null, yearOfRelease = null, albumId = null;
				
				if(requestParamMap.containsKey(ALBUM_ID)) {
					albumId = (requestParamMap.get(ALBUM_ID)[0] != null) ? requestParamMap.get(ALBUM_ID)[0].trim() : null;
				}
				
				if(requestParamMap.containsKey(NAME_OF_ALBUM)) {
					nameOfAlbum = (requestParamMap.get(NAME_OF_ALBUM)[0] != null) ? requestParamMap.get(NAME_OF_ALBUM)[0].trim() : null;
				}
				if(requestParamMap.containsKey(GENRE)) {
					genre = (requestParamMap.get(GENRE)[0] != null) ? requestParamMap.get(GENRE)[0].trim() : null;
				}
				if(requestParamMap.containsKey(MUSIC_BY)) {
					musicBy = (requestParamMap.get(MUSIC_BY)[0] != null) ? requestParamMap.get(MUSIC_BY)[0].trim() : null;
				}
				if(requestParamMap.containsKey(YEAR_OF_RELEASE)) {
					yearOfRelease = (requestParamMap.get(YEAR_OF_RELEASE)[0] != null) ? requestParamMap.get(YEAR_OF_RELEASE)[0].trim() : null;
				}
				
				logger.info("Details entered : Update Album >> nameOfAlbum : "+nameOfAlbum + ", genre : "+genre +", musicBy : "+musicBy +", yearOfRelease : "+yearOfRelease);
				
				// Update Album
				boolean isAlbumUpdated = albumDao.updateAlbumDetails(nameOfAlbum, genre, musicBy, Integer.parseInt(yearOfRelease), Integer.parseInt(albumId), userId, userName);
				
				if(isAlbumUpdated) {
					status = "Y";
				}
				
				logger.info("Is album updated : "+isAlbumUpdated +", status : "+status);
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in addAlbum() :::: AlbumController : "+e +"\n");
		}
		
		return status;
	}
	
	
	/* Delete Album
	 * */
	@RequestMapping(value=MusicStoreConstants.DELETE_ALBUM, method=RequestMethod.POST)
	@ResponseBody
	private String deleteAlbum(HttpServletRequest request, HttpSession session) {
		String status = "N";
		logger.info("\n\n");
		logger.info("Inside deleteAlbum() :::: AlbumController ");
		logger.info("Delete Album");
		
		try {
			String albumId = request.getParameterMap().containsKey(ALBUM_ID) ? request.getParameterMap().get(ALBUM_ID)[0].trim() : null;
			
			if(session != null && albumId != null) {
				JSONObject userDetails = (JSONObject) session.getAttribute(MusicStoreConstants.MS_USER_DETAILS);
				int userId = Integer.parseInt(userDetails.getString("user_id"));
				String userName = userDetails.getString("user_name");
				
				boolean isAlbumDeleted = albumDao.deleteAlbumDetails(Integer.parseInt(albumId), userId, userName);
				
				if(isAlbumDeleted) {
					status = "Y";
				}
				logger.info("Is album deleted : "+isAlbumDeleted +", status : "+status);
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in deleteAlbum() :::: AlbumController : "+e +"\n");
		}
		return status;
	}
}

