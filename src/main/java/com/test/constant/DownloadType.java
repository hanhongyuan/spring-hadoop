package com.test.constant;

public enum DownloadType {
	
	TBOX_UPGRADE_PKG(1,"/opt/tsp/tbox/firmware/","tbox远程升级包"),
	TBOX_UPLOAD_LOG(2,"/opt/tsp/tbox/log/","tbox上传日志"),
	AVN_UPLOAD_IMG(3,"/opt/tsp/images/avn/","娱乐主机上传抓拍图片"),
	APP_HEAD_PIC(4,"/opt/tsp/images/app/head/","app上传头像"),
	AVN_UPGRADE_PKG(5,"/opt/tsp/avn/firmware/","avn远程升级包");
	
	private int type;
	
	private String rootPath;
	
	private String message;
	
	private DownloadType(int type, String rootPath, String message) {
		this.type = type;
		this.rootPath = rootPath;
		this.message = message;
	}
	
	public static DownloadType getByType(int type) {
		switch (type) {
		case 1: return TBOX_UPGRADE_PKG;
		case 2: return TBOX_UPLOAD_LOG;
		case 3: return AVN_UPLOAD_IMG;
		case 4: return APP_HEAD_PIC;
		case 5: return AVN_UPGRADE_PKG;
		default: return null;
		}
	}
	
	public int getType() {
		return type;
	}
	
	public String getRootPath() {
		return rootPath;
	}
	
	public String getMessage() {
		return message;
	}

}
