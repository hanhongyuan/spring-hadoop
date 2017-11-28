package com.test.util;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.test.config.HdfsProperties;

/**
 * 参考：http://www.mamicode.com/info-detail-1719318.html
 * 
 * @ClassName: HdfsUtil.java
 * @Description: 上传文件到hdfs
 * @author 尹顺林
 * @version V1.0
 * @Date 2017年11月24日 下午3:52:53
 */
public class HdfsUtil {

	private static final Logger logger = LogManager.getLogger(HdfsUtil.class);

	private HdfsProperties hdfsProperties;

	public HdfsUtil(HdfsProperties hdfsProperties) {
		this.hdfsProperties = hdfsProperties;
	}

	public HdfsProperties getHdfsProperties() {
		return hdfsProperties;
	}

	public static void main(String[] args) throws IOException {
		HdfsUtil hdfsUtil = new HdfsUtil(null);
		hdfsUtil.uploadFile("", "");
	}

	public void uploadFile(String... args) throws IOException {
		Configuration conf = new Configuration();

		// conf.setBoolean(CROSS_PLATFORM, true);
		logger.info("host: " + hdfsProperties.getHost());
		logger.info("defaultfs: " + hdfsProperties.getDefaultfs());
		conf.set(hdfsProperties.getDefaultfs(), hdfsProperties.getHost());

		GenericOptionsParser optionsParser = new GenericOptionsParser(conf, args);

		String[] remainingArgs = optionsParser.getRemainingArgs();
		if (remainingArgs.length < 2) {
			System.err.println("Usage: upload <source> <dest>");
			System.exit(2);
		}

		Path source = new Path(args[0]);
		Path dest = new Path(args[1]);

		logger.info("source::::: " + source.toUri().getPath());
		logger.info("dest:::: " + dest.toUri().getPath());
		FileSystem fs = FileSystem.get(conf);

		fs.copyFromLocalFile(true, false, source, dest);
	}
}
