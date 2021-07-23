package com.musicstore.dao;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.musicstore.constants.MusicStoreConstants;
import com.musicstore.helper.JSONArrayExtractor;
import com.musicstore.helper.JSONObjectExtractor;

public class BaseDao {
	private static final Logger logger = Logger.getLogger(BaseDao.class);
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Object selectRecords(String sqlQuery, Object params[], String resultSetType) {
		logger.info("Inside selectRecords() :::: BaseDao");
		logger.info("SQL query : "+sqlQuery);
		logger.info("Params : "+Arrays.toString(params));
		logger.info("ResultSet type : "+resultSetType);
		
		Object dbResults = null;
		try {
			if(resultSetType.equals(MusicStoreConstants.RESULTSET_TYPE_INTEGER)) {
				dbResults = jdbcTemplate.queryForObject(sqlQuery, params, Integer.class);
			}
			else if(resultSetType.equals(MusicStoreConstants.RESULTSET_TYPE_STRING)) {
				dbResults = jdbcTemplate.queryForObject(sqlQuery, params, String.class);
			}
			else if(resultSetType.equals(MusicStoreConstants.RESULTSET_TYPE_JSON_OBJECT)) {
				JSONObjectExtractor jsonObjectExtractor = new JSONObjectExtractor();
				dbResults = jdbcTemplate.query(sqlQuery, params, jsonObjectExtractor);
			}
			else if(resultSetType.equals(MusicStoreConstants.RESULTSET_TYPE_JSON_ARRAY)) {
				JSONArrayExtractor jsonArrayExtractor = new JSONArrayExtractor();
				dbResults = jdbcTemplate.query(sqlQuery, params, jsonArrayExtractor);
			}
		}
		catch(EmptyResultDataAccessException e) {
			logger.error("\n\nEmptyResultDataAccessException occurred in selectRecords() :::: BaseDao : "+e);
			return null;
		}
		catch(DataAccessException e) {
			logger.error("\n\nDataAccessException occurred in selectRecords() :::: BaseDao : "+e);
			return null;
		}
		logger.info("DB results : "+dbResults);
		return dbResults;
	}
	
	
	public int insertRecords(String sqlQuery, Object params[]) {
		logger.info("Inside insertRecords() :::: BaseDao");
		logger.info("SQL query : "+sqlQuery);
		logger.info("Params : "+Arrays.toString(params));
		
		int count = 0;
		try {
			count = jdbcTemplate.update(sqlQuery, params);
		}
		catch(DataAccessException e) {
			logger.error("\n\nDataAccessException occurred in insertRecords() :::: BaseDao : "+e);
			return 0;
		}
		logger.info("No of rows inserted : "+count);
		return count;
	}
	
	public int updateRecords(String sqlQuery, Object params[]) {
		logger.info("Inside updateRecords() :::: BaseDao");
		logger.info("SQL query : "+sqlQuery);
		logger.info("Params : "+Arrays.toString(params));
		
		int count = 0;
		try {
			count = jdbcTemplate.update(sqlQuery, params);
		}
		catch(DataAccessException e) {
			logger.error("\n\nException occurred in updateRecords() :::: BaseDao : "+e);
			return 0;
		}
		logger.info("No of rows updated : "+count);
		return count;
	}
	
}
