package com.musicstore.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.dao.CommonDao;
import com.musicstore.dao.FileDao;
import com.musicstore.utils.MusicStoreUtils;
import com.musicstore.vo.FileVO;

@Controller
public class FileController {
	private static Logger logger = Logger.getLogger(FileController.class);
	
	private FileDao fileDao;

	public FileDao getFileDao() {
		return fileDao;
	}

	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}
	
	private static final String COMMENTS = "comments_";
	private static final String FILE_ID = "fileid";
	
	
	@RequestMapping(value=MusicStoreConstants.LOAD_FILE, method=RequestMethod.GET)
	private ModelAndView loadFile(HttpServletRequest request, HttpSession session) {
		logger.info("\n\n");
		logger.info("Inside loadFile() :::: FileController");
		logger.info("Load File to Music Store");
		
		ModelAndView mv = new ModelAndView();
		try {
			boolean isValidLogin = MusicStoreUtils.authenticateLogin(request);
			String view = (session != null && isValidLogin) ? MusicStoreConstants.MS_FILE_UPLOAD_PORTLET : MusicStoreConstants.MS_REDIRECT_TO_HOME;
			mv.setViewName(view);
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in loadFile() :::: FileController : "+e +"\n");
		}
		return mv;
	}
	
	
	@RequestMapping(value=MusicStoreConstants.UPLOAD_FILE, consumes=MediaType.MULTIPART_FORM_DATA_VALUE, method=RequestMethod.POST)
	@ResponseBody
	private String uploadFile(HttpServletRequest request, @RequestParam("files") CommonsMultipartFile[] files) {
		String fileUpload = "N";
		logger.info("\n\n");
		logger.info("Inside uploadFile() :::: FileController");
		logger.info("Upload File to Music Store");
		
		try {
			Map<String, String[]> mapRequestParams = request.getParameterMap();
			
			JSONObject userDetails = CommonDao.getMsUserDetails(request);
			int userId = userDetails.getInt("user_id");
			String userName = userDetails.getString("user_name");
			int fileIndex = 1;
			List<FileVO> fileList = new ArrayList<>();
			
			for(CommonsMultipartFile file : files) {
				boolean isValidFile = fileDao.validateFile(file);
				
				if(isValidFile) {
					String fileName = file.getOriginalFilename();
					int fileSize = (int) file.getSize();
					// String fileType = file.getContentType();
					byte fileByteArr[] = file.getBytes();
					
					// Enocde byte array
					byte fileByteArrEnc[] = Base64.getEncoder().encode(fileByteArr); // Java 8
					// byte fileByteArrEnc[] = org.apache.tomcat.util.codec.binary.Base64.encodeBase64(fileByteArr); // Apache
					
					/* String fileBytesStr = new String(fileByteArrEnc);
					System.out.println("fileBytes length : "+fileBytesStr.length());
					System.out.println("After encode : "+fileBytesStr); */
					
					String fileExt = fileName.split("\\.")[1];
					String comments = mapRequestParams.containsKey(COMMENTS+fileIndex) ? mapRequestParams.get(COMMENTS+fileIndex)[0] : "N.A";
					
					FileVO fileVO = new FileVO();
					fileVO.setFileName(fileName);
					fileVO.setFileType(fileExt);
					fileVO.setFileSize(fileSize);
					fileVO.setFileBytes(fileByteArrEnc);
					fileVO.setComments(comments);
					fileVO.setCreatedBy(userId);
					fileVO.setUserId(userId);
					fileVO.setUserName(userName);
					
					// Call file upload
					fileVO = fileDao.uploadFile(fileVO);
					String errorCode = fileVO.getErrorCode();
					
					if(errorCode != null && errorCode.equalsIgnoreCase(MusicStoreConstants.FILE_UPLOAD_SUCCESS_CODE)) {
						fileUpload = "Y";
					}
					
					fileList.add(fileVO);
				}
				fileIndex++;
			}
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in uploadFile() :::: FileController : "+e +"\n");
		}
		return fileUpload;
	}
	
	
	@RequestMapping(value=MusicStoreConstants.VIEW_FILE, method=RequestMethod.GET)
	@ResponseBody
	public String viewFileDetails(HttpServletRequest request) {
		String fileDetails = null;
		logger.info("\n\n");
		logger.info("Inside viewFileDetails() :::: FileController");
		logger.info("View File details to Music Store");
		
		try {
			JSONObject userDetails = CommonDao.getMsUserDetails(request);
			int userId = userDetails.getInt("user_id");
			String userName = userDetails.getString("user_name");
			
			// Get file details
			fileDetails = fileDao.getFileDetails(userId, userName);
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in viewFileDetails() :::: FileController : "+e +"\n");
		}
		return fileDetails;
	}
	
	
	@RequestMapping(value=MusicStoreConstants.DOWNLOAD_FILE, method=RequestMethod.POST)
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
		logger.info("\n\n");
		logger.info("Inside downloadFile() :::: FileController");
		logger.info("Download File to Music Store");
		Map<String, String[]> mapRequestParams = request.getParameterMap();
		
		try {
			JSONObject userDetails = CommonDao.getMsUserDetails(request);
			int userId = userDetails.getInt("user_id");
			String userName = userDetails.getString("user_name");
			
			String fileId = mapRequestParams.getOrDefault(FILE_ID, null)[0];
			logger.debug("file id : "+fileId);
			
			JSONObject downloadFileDetails = fileDao.getDownloadFileDetails(userId, userName, fileId);
			if(downloadFileDetails != null && downloadFileDetails.length() > 0) {
				String fileName = downloadFileDetails.has("file_name") ? downloadFileDetails.getString("file_name") : null;
				String fileByteStr = downloadFileDetails.has("file_blob") ? downloadFileDetails.getString("file_blob") : null;
				/* String mimeType = URLConnection.guessContentTypeFromName(fileName);
				response.setContentType(mimeType); */
				
				// Set HttpServletResponse headers
				response.setContentType("application/force-download"); // force dowload the file onclick event
				response.setHeader("Content-Transfer-Encoding", "binary");
				response.setHeader("Content-Disposition", "attachment; filename=\"" +fileName +"\"");
				
				byte fileByteArrEnc[] = fileByteStr.getBytes();
				
				// Decode byte array
				byte fileByteArrayDec[] = Base64.getDecoder().decode(fileByteArrEnc); // Java 8
				// byte fileByteArray[] = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(fileByteArrayEnc); // Apache 
				
				ByteArrayInputStream inputStream = new ByteArrayInputStream(fileByteArrayDec);
				// InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(fileByteArrayDec));
				
				IOUtils.copy(inputStream, response.getOutputStream());
				response.flushBuffer();
			}
			
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in downloadFile() :::: FileController : "+e +"\n");
		}
	}
	
	
	@RequestMapping(value=MusicStoreConstants.DELETE_FILE, method=RequestMethod.POST)
	@ResponseBody
	public String deleteFile(HttpServletRequest request) {
		String deleteFile = "N";
		logger.info("\n\n");
		logger.info("Inside deleteFile() :::: FileController");
		logger.info("Upload File to Music Store");
		Map<String, String[]> mapRequestParams = request.getParameterMap();
		
		try {
			JSONObject userDetails = CommonDao.getMsUserDetails(request);
			int userId = userDetails.getInt("user_id");
			String userName = userDetails.getString("user_name");
			
			String fileId = mapRequestParams.getOrDefault(FILE_ID, null)[0];
			logger.debug("file id : "+fileId);
			
			// delete file (logical deletion)
			deleteFile = fileDao.deleteFileDetails(userId, userName, fileId);
		}
		catch(Exception e) {
			logger.error("\n\nException occurred in deleteFile() :::: FileController : "+e +"\n");
		}
		return deleteFile;
	}
}
