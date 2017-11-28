package com.test.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.util.HdfsUtil;
import com.test.util.ThrowableUtil;

@RestController
public class UploadDownloadController {

	private static final Logger logger = LogManager.getLogger(UploadDownloadController.class);
	
	@Autowired
	private HdfsUtil hdfsUtil;

	@RequestMapping(value = "/uploadToHdfs", method = { RequestMethod.POST })
	public String uploadToHdfs(HttpServletRequest request, @RequestParam("file") MultipartFile file)
	        throws IllegalStateException, IOException {
		logger.info("uploadToHdfs started....");
		if (!file.isEmpty()) {
			logger.info("file.size... " + file.getSize());
			try {
				String originalFilename = file.getOriginalFilename();

				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(originalFilename)));

				out.write(file.getBytes());

				out.flush();
				out.close();

				String destFileName = hdfsUtil.getHdfsProperties().getUploadPath() + originalFilename;
			
				logger.info("destFileName::: " + destFileName);
				
				hdfsUtil.uploadFile(new String[] { originalFilename, destFileName });

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				logger.error(ThrowableUtil.getErrorInfoFromThrowable(e));
				return "上传失败，" + e.getMessage();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(ThrowableUtil.getErrorInfoFromThrowable(e));
				return "上传失败, " + e.getMessage();
			}

			return "上传成功";

		}
		return "上传失败，文件为空。";
	}

}
