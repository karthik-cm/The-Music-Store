package com.musicstore.vo;

import java.util.Arrays;

public class FileVO {
	private String fileName;
	private String fileType;
	private int fileSize;
	private byte fileBytes[];
	private String comments;
	private int userId;
	private String userName;
	private int createdBy;
	private String errorCode;
	private String errorDesc;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public byte[] getFileBytes() {
		return fileBytes;
	}
	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
	@Override
	public String toString() {
		return "FileVO [fileName=" + fileName + ", fileType=" + fileType + ", fileSize=" + fileSize + ", fileBytes="
				+ Arrays.toString(fileBytes) + ", comments=" + comments + ", userId=" + userId + ", userName="
				+ userName + ", createdBy=" + createdBy + ", errorCode=" + errorCode + ", errorDesc=" + errorDesc + "]";
	}
	
}
