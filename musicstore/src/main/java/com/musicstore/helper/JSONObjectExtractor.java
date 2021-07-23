package com.musicstore.helper;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONObject;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.musicstore.constants.MusicStoreConstants;

public class JSONObjectExtractor implements ResultSetExtractor<JSONObject> {
	
	public JSONObject extractData(ResultSet rs) throws SQLException {
		JSONObject resultSetJsonObj = new JSONObject();
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			
			if(rs.next()) {
				for(int i=1; i<=rsmd.getColumnCount(); i++) {
					String columnName = rsmd.getColumnName(i);
					
					if(rs.getString(columnName) != null) {
						if(rsmd.getColumnTypeName(i).equalsIgnoreCase(MusicStoreConstants.RSMD_TYPE_DATE)) {
							resultSetJsonObj.put(columnName.toLowerCase(), rs.getDate(columnName));
						} 
						else {
							resultSetJsonObj.put(columnName.toLowerCase(), rs.getString(columnName));
						}
					}
					else if(rs.getBlob(columnName) != null){ 
						if(rsmd.getColumnTypeName(i).equalsIgnoreCase(MusicStoreConstants.RSMD_TYPE_BLOB)) {
							Blob blob = rs.getBlob(columnName);
							byte fileByteArrEnc[] = blob.getBytes(1, (int) blob.length());
							String fileBytesStr = new String(fileByteArrEnc);
							resultSetJsonObj.put(columnName.toLowerCase(), fileBytesStr);
						}
					}
					else {
						resultSetJsonObj.put(columnName, MusicStoreConstants.EMPTY_STRING);
					}
				}
			}
		}
		catch(Exception e) {
			return null;
		}
		
		if(resultSetJsonObj.length() > 0)
			return resultSetJsonObj;
		else
			return null;
	}
}