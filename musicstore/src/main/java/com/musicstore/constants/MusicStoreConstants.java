package com.musicstore.constants;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MusicStoreConstants {
	// Regex
	public static final String VALID_NAME_REGEX = "[a-zA-Z]*$";
	public static final String VALID_USERNAME_REGEX = "[a-zA-Z0-9_]*$";
	public static final String VALID_CONTACT_NO = "[0-9]*";
	public static final String VALID_EMAIL_REGEX = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
	
	public static final String ALPHA_REGEX = "[a-zA-Z]*$";
	public static final String ALPHA_NUMERIC_REGEX_3 = "[a-zA-Z0-9_ ]*";
	public static final String ALPHA_NUMERIC_REGEX_4 = "[a-zA-Z0-9._ ]*";
	
	
	// Deicmal Format
	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
	
	
	
	// Error msgs
	public static final String GENERIC_ERROR_MSG = "System is currently down. Kindly try after sometime.";
	public static final String INVALID_FORM_DETAILS_ERROR_MSG = "Invalid form details. Kindly check.";
	public static final String INVALID_CREDENTIALS_ERROR_MSG = "Invalid username/password. Kindly check";
	
	public static final String INVALID_FILE_NAME = "ms.file.upload.invalid.file.name";
	public static final String INVALID_FILE_NAME_LENGTH = "ms.file.upload.invalid.file.name.length";
	public static final String INVALID_FILE_NAME_SPL = "ms.file.upload.invalid.file.name.spl";
	public static final String INVALID_FILE_TYPE = "ms.file.upload.invalid.file.type";
	public static final String INVALID_EMPTY_FILE = "ms.file.upload.invalid.empty.file";
	public static final String INVALID_FILE_SIZE = "ms.file.upload.invalid.file.size";
	
	
	// Session Attributes
	public static final String MS_USER_DETAILS = "MS_USER_DETAILS"; // to store user details 
	public static final String MS_VALID_LOGIN = "MS_VALID_LOGIN";
	
	
	// RequestMapping Values
	public static final String HOME = "/home";
	public static final String CHECK_USERNAME_AVAILABILITY = "/checkUserNameAvailability";
	public static final String SIGN_UP = "/signUp";
	public static final String LOGIN = "/login";
	public static final String LOGOUT = "/logout";
	public static final String DASHBOARD = "/dashboard";
	public static final String HANDLE_ERROR = "/handleError";
	
	
	// Pages
	public static final String PAGE = "page";
	public static final String MS_HOME_PAGE = "ms_home";
	public static final String MS_DASHBOARD_PAGE = "ms_dashboard";
	
	
	// Portlets
	public static final String PORTLET = "portlet";
	public static final String MS_PROFILE_PORTLET = "ms_profile";
	public static final String MS_CHANGE_PWD_PORTLET = "ms_change_pwd";
	public static final String MS_ALBUM_PORTLET = "ms_album";
	public static final String MS_TRACK_PORTLET = "ms_track";
	public static final String MS_FILE_UPLOAD_PORTLET = "ms_file_upload";
	public static final String MS_HANDLE_ERROR_PORTLET = "ms_handle_error";
	
	
	// Spring request > Redirection
	public static final String MS_REDIRECT_TO_HOME = "redirect:"+HOME;
	
	
	public static final List<String> PORTLET_LIST = new ArrayList<String>();
	
	static {
		// Portlet List
		PORTLET_LIST.add(MS_PROFILE_PORTLET);
		PORTLET_LIST.add(MS_CHANGE_PWD_PORTLET);
		PORTLET_LIST.add(MS_ALBUM_PORTLET);
		PORTLET_LIST.add(MS_TRACK_PORTLET);
		PORTLET_LIST.add(MS_FILE_UPLOAD_PORTLET);
		PORTLET_LIST.add(MS_HANDLE_ERROR_PORTLET);
	}
	
	
	// Load Portlet
	public static final String LOAD_PORTLET = "/loadPortlet";
		
	
	// Profile Functions
	public static final String LOAD_PROFILE = "/profile";
	public static final String UPDATE_PROFILE = "/updateProfile";
	
	// Profile Functions
	public static final String LOAD_CHANGE_PWD = "/changePwd";
	public static final String CHANGE_PWD = "/changePwd";
	
	
	// Album Functions
	public static final String LOAD_ALBUM = "/album";
	public static final String ADD_ALBUM = "/addAlbum";
	public static final String VIEW_ALBUM = "/viewAlbum";
	public static final String UPDATE_ALBUM = "/updateAlbum";
	public static final String DELETE_ALBUM = "/deleteAlbum";
	
	// Track Functions
	public static final String LOAD_TRACK = "/track";
	public static final String ADD_TRACK = "/addTrack";
	public static final String VIEW_TRACK = "/viewTrack";
	public static final String UPDATE_TRACK = "/updateTrack";
	public static final String DELETE_TRACK = "/deleteTrack";
	
	
	// Track Functions
	public static final String LOAD_FILE = "/file";
	public static final String UPLOAD_FILE = "/uploadFile";
	public static final String VIEW_FILE = "/viewFile";
	public static final String DELETE_FILE = "/deleteFile";
	public static final String DOWNLOAD_FILE = "/downloadFile";
	
	
	// ResultSet Type
	public static final String RESULTSET_TYPE_STRING = "RESULTSET_TYPE_STRING";
	public static final String RESULTSET_TYPE_INTEGER = "RESULTSET_TYPE_INTEGER";
	public static final String RESULTSET_TYPE_JSON_OBJECT = "RESULTSET_TYPE_JSON_OBJECT";
	public static final String RESULTSET_TYPE_JSON_ARRAY = "RESULTSET_TYPE_JSON_ARRAY";
	
	public static final String RSMD_TYPE_STRING = "STRING";
	public static final String RSMD_TYPE_DATE = "DATE";
	public static final String RSMD_TYPE_BLOB = "BLOB";
	public static final String EMPTY_STRING = "";
	
	
	// Key constants
	public static final String MS_PROPERTY_FILE = "musicstore.properties";
	public static final String MS_SYSTEM = "MS_SYSTEM";
	public static final String MS_ACTIVE_USER = "Y";
	public static final String MS_ROLE_USER = "USER";
	public static final String MS_ROLE_ADMIN = "ADMIN";
	public static final String NOT_AVALIABLE = "NA";
	
	public static final String MS_ACTIVE_FLAG_Y = "Y";
	public static final String MS_ACTIVE_FLAG_N = "N";
	
	public static final String MS_AES_KEY = "r2jxkgz3yvc76xte";
	
	public static final String HTTP_ERROR_STATUS_CODE = "javax.servlet.error.status_code";
	
	
	
	// File Upload
	public static final int EMPTY_FILE_SIZE = 10;
	public static final int MAX_FILE_SIZE = 10;
	public static final String MS_FILE_TYPE = "jpg|jpeg|png|pdf";
	public static final String FILE_UPLOAD_SUCCESS_CODE = "0";
	public static final String FILE_UPLOAD_SUCCESS_DESC = "Success";
	public static final String FILE_UPLOAD_ERROR_CODE = "999";
	public static final String FILE_UPLOAD_ERROR_DESC = "Error";
}
