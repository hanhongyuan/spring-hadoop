package com.test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hdfs", ignoreUnknownFields = false)
public class HdfsProperties {
	
	private String defaultfs;
	
	private String host;
	
	private String uploadPath;
	
	public String getDefaultfs() {
		return defaultfs;
	}

	public void setDefaultfs(String defaultfs) {
		this.defaultfs = defaultfs;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

}
