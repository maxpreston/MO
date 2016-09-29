package mo.oa.io.mo.Entities;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class filevo implements Serializable{
	private String author;
	private String createTimeFormat;
	private byte[] fileData;
	private String fileId;
	private String fileName;
	private int fileSize;
	
	public filevo(String author, String createTimeFormat,byte[] fileData,String fileId,String fileName,int fileSize) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileData = fileData;
		this.author = author;
		this.fileSize = fileSize;
		this.createTimeFormat = createTimeFormat;
	}
	
	public filevo(byte[] fileData) {
		super();
		this.fileData = fileData;
	}


	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public String getCreateTimeFormat() {
		return createTimeFormat;
	}
	public void setCreateTimeFormat(String createTimeFormat) {
		this.createTimeFormat = createTimeFormat;
	}
}
