package com.musicstore.constants;

public class MusicStoreSQLConstants {
	
	public static final String CHECK_USER_NAME_AVAILABILITY = "Select count(*) from ms_user_details where user_name = ?";
	
	public static final String INSERT_NEW_MS_USER_DETAILS = "Insert into ms_user_details values (ms_user_details_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, null, null)";
	
	
	public static final String CHECK_USER_EXISTS = "Select user_id, password from ms_user_details where user_name = ?";
	
	public static final String GET_MS_USER_DETAILS_ID = "Select user_id, first_name, last_name, contact_no, email_id, user_name, password, is_active_flag, role from ms_user_details where user_id = ?";
	
	public static final String GET_MS_USER_DETAILS_NAME = "Select user_id, first_name, last_name, contact_no, email_id, user_name, password, is_active_flag, role from ms_user_details where user_name = ?";
	
	public static final String GET_LAST_LOGGED_IN_TIME = "Select NVL(to_char(max(log_in_time), 'DD-MON-YYYY HH:MI AM'), 'NA') as last_logged_in_time from ms_login_history where user_id = ? and user_name = ?";
	
	public static final String INSERT_LOGIN_DETAILS = "Insert into ms_login_history values (ms_login_history_seq.nextval, ?, ?, ?, SYSDATE, null, null)";
	
	public static final String UPDATE_LOGOUT_DETAILS = "Update ms_login_history set log_out_time=SYSDATE, record_updated_by=? where login_trans_rk=(Select max(login_trans_rk) from ms_login_history where user_id=? and user_name=? and log_out_time is null)";
	
	
	
	// Profile
	public static final String UPDATE_PROFILE_DETAILS = "Update MS_USER_DETAILS set first_name=?, last_name=?, contact_no=?, email_id=?, user_name=?, record_updated_by=?, record_updated_dt=sysdate where user_id = ?";
	
	
	// Password
	public static final String UPDATE_PASSWORD_DETAILS = "Update MS_USER_DETAILS set password=?, record_updated_by=?, record_updated_dt=sysdate where user_id=? and user_name=?";
	
	
	
	// Album 
	public static final String INSERT_NEW_ALBUM_DETAILS = "Insert into MS_ALBUM_DETAILS values (ms_album_details_seq.nextval, ?, ?, ?, ?, ?, ?, ?, sysdate, null, null, ?)";
	
	public static final String GET_ALBUM_DETAILS = "Select album_id, album_name, genre, music_by, year_of_release, to_char(record_created_dt, 'DD-MM-YYYY HH:MI:SS PM') as created_date from MS_ALBUM_DETAILS where user_id = ? and user_name = ? and active_flag = 'Y' order by record_created_dt";
	
	public static final String UPDATE_ALBUM_DETAILS = "Update MS_ALBUM_DETAILS set album_name = ?, genre = ?, music_by = ?, year_of_release = ?, record_updated_by = ?, record_updated_dt = SYSDATE where album_id = ? and user_id = ? and user_name = ?";
	
	public static final String CHECK_ALBUM_BELONGS_TO_USER = "Select count(*) from MS_ALBUM_DETAILS where album_id = ? and user_id = ? and user_name = ?";
	
	public static final String DELETE_ALBUM_DETAILS = "Update MS_ALBUM_DETAILS set active_flag = 'N' where album_id = ? and user_id = ? and user_name = ?";
	
	
	
	// Track
	public static final String INSERT_NEW_TRACK_DETAILS = "Insert into MS_TRACK_DETAILS values (ms_track_details_seq.nextval, ?, ?, ?, ?, ?, ?, sysdate, null, null, ?)";
	
	public static final String GET_TRACK_DETALS = "SELECT a.user_id, b.album_id, b.album_name, c.track_id, c.track_name, c.artists, c.duration, c.sequence_no, to_char(c.record_created_dt, 'DD-MM-YYYY HH:MI AM') as created_dt, (SELECT COUNT(*) FROM ms_track_details WHERE active_flag = 'Y' AND album_id = b.album_id) track_count FROM ms_user_details a LEFT JOIN ms_album_details b ON a.user_id = b.user_id LEFT JOIN ms_track_details c ON b.album_id = c.album_id AND c.active_flag = 'Y' WHERE a.user_id = ? AND b.active_flag = 'Y' ORDER BY b.album_id, c.sequence_no";
	
	public static final String UPDATE_TRACK_DETAILS = "Update MS_TRACK_DETAILS set album_id=?, track_name=?, artists=?, duration=?, sequence_no=? where track_id=? and record_created_by = (select user_name from MS_USER_DETAILS where user_id = ?)";
	
	public static final String DELETE_TRACK_DETAILS = "Update MS_TRACK_DETAILS set active_flag = 'N' where track_id = ? and record_created_by = ?";

	
	
	// File
	public static final String INSERT_NEW_FILE_DETAILS  = "Insert into ms_file_details (file_id, user_id, file_name, file_type, file_size, file_blob, comments, record_created_by, record_created_dt, record_updated_by, record_updated_dt) values(ms_file_details_seq.nextval, ?, ?, ?, ?, ?, ?, ?, sysdate, null, null)";
	
	public static final String GET_FILE_DETAILS = "Select file_id, file_name, file_type, file_size, comments, NVL(to_char(record_created_dt, 'DD-MON-YYYY HH:MI AM'), 'NA') created_date from ms_file_details where active_flag = 'Y' and user_id = ?";
	
	public static final String CHECK_FILE_BELONGS_TO_USER = "Select count(*) from ms_file_details where file_id = ? and user_id = ?";
	
	public static final String UPDATE_FILE_ACTIVE_FLAG = "Update ms_file_details set active_flag = 'N', record_updated_by = ?, record_updated_dt = sysdate where file_id = ? and user_id = ?";
	
	public static final String GET_FILE_DOWNLOAD_DETAILS = "Select file_name, file_blob from ms_file_details where file_id = ?";
}
