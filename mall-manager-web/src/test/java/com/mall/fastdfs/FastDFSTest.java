package com.mall.fastdfs;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class FastDFSTest {

	@Test
	public void testUpload() throws FileNotFoundException, IOException, MyException {
		ClientGlobal.init("E:/StudyWorkSpace//java//mall//mall-manager-web//src//main//resources//resource//client.conf");
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer connection = trackerClient.getConnection();
		StorageClient storageClient = new StorageClient(connection, null);
		String[] upload_files = storageClient.upload_file("D:/desktop/tupian.jpg", "jpg", null);
		for(String file : upload_files) {
			System.out.println("测试显示:" + file);
		}
	}
	
	@Test
	public void testPath() {
		String path = this.getClass().getResource("/").getPath();
		System.out.println("显示路劲:" + path);
	}
}
