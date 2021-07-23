package com.musicstore.dao;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.constants.MusicStoreSQLConstants;
import com.musicstore.utils.PropertyUtil;
import com.musicstore.vo.FileVO;

public class FileDao {
	private static final Logger logger = Logger.getLogger(FileDao.class);
	
	private BaseDao baseDao;
	
	public FileDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public boolean validateFile(CommonsMultipartFile file) {
		logger.info("Inside validateFile() :::: FileDao "); 
		boolean isValidFile = true;
		try {
			if(file != null) { //&& !file.isEmpty()
				String fullFileName = file.getOriginalFilename();
				long fileSize = file.getSize();
				String fileType = file.getContentType();
				String fileNameArr[] = {};
				String fileName = null, fileExt = null, errorMsg = null;
				
				if(fullFileName != null && fullFileName.length() > 0) {
					fileNameArr = fullFileName.split("\\.");
					fileName = fileNameArr[0];
					fileExt = fileNameArr[1];
				}
				
				if(fullFileName != null && fullFileName.length() > 2) {
					if(fileNameArr.length > 2) {
						errorMsg = PropertyUtil.getPropertyValue(MusicStoreConstants.INVALID_FILE_NAME) +fileName;
						isValidFile = false;
					}
				}
				else if(fileName != null) {
					if(fileName.length() > 100) {
						errorMsg = PropertyUtil.getPropertyValue(MusicStoreConstants.INVALID_FILE_NAME_LENGTH);
						isValidFile = false;
					}
					else if(!fileName.matches(MusicStoreConstants.ALPHA_NUMERIC_REGEX_3)) {
						errorMsg = PropertyUtil.getPropertyValue(MusicStoreConstants.INVALID_FILE_NAME_SPL);
						isValidFile = false;
					}
				}
				else if(fileExt.equals(fileType.split("/")[1]) && fileExt.toLowerCase().matches(MusicStoreConstants.MS_FILE_TYPE)) {
					errorMsg = PropertyUtil.getPropertyValue(MusicStoreConstants.INVALID_FILE_TYPE);
					isValidFile = false;
				}
				else if(fileSize == MusicStoreConstants.EMPTY_FILE_SIZE) {
					errorMsg = PropertyUtil.getPropertyValue(MusicStoreConstants.INVALID_EMPTY_FILE);
					isValidFile = false;
				}
				else if((fileSize / (1024*1024)) > MusicStoreConstants.MAX_FILE_SIZE) {
					errorMsg = PropertyUtil.getPropertyValue(MusicStoreConstants.INVALID_FILE_SIZE);
					isValidFile = false;
				}
				
				if(!isValidFile) {
					logger.debug("Error : "+errorMsg);
				}
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in validateFile() :::: FileDao : "+e);
		}
		return isValidFile;
	}

	public FileVO uploadFile(FileVO fileVO) {
		logger.info("Inside uploadFile() :::: FileDao "); 
		if(fileVO != null) {
			Object params[] = {fileVO.getUserId(), fileVO.getFileName(), fileVO.getFileType(), fileVO.getFileSize(), fileVO.getFileBytes(), fileVO.getComments(), fileVO.getUserName()};
			try {
				int insertCount = baseDao.insertRecords(MusicStoreSQLConstants.INSERT_NEW_FILE_DETAILS, params);
				if(insertCount > 0) {
					logger.info("File is inserted successfully");
					fileVO.setErrorCode(MusicStoreConstants.FILE_UPLOAD_SUCCESS_CODE);
					fileVO.setErrorDesc(MusicStoreConstants.FILE_UPLOAD_SUCCESS_DESC);
				}
				else {
					logger.info("File insertion failed");
					fileVO.setErrorCode(MusicStoreConstants.FILE_UPLOAD_ERROR_CODE);
					fileVO.setErrorDesc(MusicStoreConstants.FILE_UPLOAD_ERROR_DESC);
				}
				logger.info("File upload details : fileVO : "+fileVO);
			}
			catch(Exception e) {
				logger.error("\n\nException occurred in uploadFile() :::: FileDao : "+e);
			}
		}
		return fileVO;
	}

	public String getFileDetails(int userId, String userName) {
		logger.info("Inside getFileDetails() :::: FileDao - userId : "+userId +", userName : "+userName);
		String fileDetails = "";
		try {
			JSONArray fileDetailsObj = (JSONArray) baseDao.selectRecords(MusicStoreSQLConstants.GET_FILE_DETAILS, new Object[] {userId}, MusicStoreConstants.RESULTSET_TYPE_JSON_ARRAY);
			if(fileDetailsObj != null && fileDetailsObj.length() > 0) {
				logger.info("file details for userId - "+userId +" : "+fileDetailsObj);
				fileDetails = fileDetailsObj.toString();
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in getFileDetails() :::: FileDao : "+e);
		}
		return fileDetails;
	}
	
	public JSONObject getDownloadFileDetails(int userId, String userName, String fileId) {
		logger.info("Inside getDownloadFileDetails() :::: FileDao - userId : "+userId +", userName : "+userName);
		JSONObject downloadFileDetails = null;
		try {
			int fileCount = (int) baseDao.selectRecords(MusicStoreSQLConstants.CHECK_FILE_BELONGS_TO_USER, new Object[] {fileId, userId}, MusicStoreConstants.RESULTSET_TYPE_INTEGER);
			if(fileCount == 1) {
				downloadFileDetails = (JSONObject) baseDao.selectRecords(MusicStoreSQLConstants.GET_FILE_DOWNLOAD_DETAILS, new Object[] {fileId}, MusicStoreConstants.RESULTSET_TYPE_JSON_OBJECT);
				if(downloadFileDetails != null && downloadFileDetails.length() > 0) {
					logger.info("File details successfully fetched for download");
				}
				else {
					logger.info("File details could not be fetched for download");
				}
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in getDownloadFileDetails() :::: FileDao : "+e);
		}
		return downloadFileDetails;
	}
	
	public String deleteFileDetails(int userId, String userName, String fileId) {
		logger.info("Inside deleteFileDetails() :::: FileDao - userId : "+userId +", userName : "+userName);
		String deleteFile = "N";
		try {
			int fileCount = (int) baseDao.selectRecords(MusicStoreSQLConstants.CHECK_FILE_BELONGS_TO_USER, new Object[] {fileId, userId}, MusicStoreConstants.RESULTSET_TYPE_INTEGER);
			if(fileCount == 1) {
				int updateCount = (int) baseDao.updateRecords(MusicStoreSQLConstants.UPDATE_FILE_ACTIVE_FLAG, new Object[] {userName, fileId, userId});
				if(updateCount > 0) {
					deleteFile = "Y";
					logger.info("File is successfully removed");
				}
				else {
					logger.info("File could not be removed");
				}
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in deleteFileDetails() :::: FileDao : "+e);
		}
		return deleteFile;
	}
}