package com.mall.fastdfs;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.zjut.mall.utils.FastDFSClient;

public class FastDFSTest {

	@Test
	public void testUpload() throws FileNotFoundException, IOException, MyException {
		ClientGlobal.init("D:/WorkSpace/Java/mall/mall-manager-web/src/main/resources/resource/client.conf");
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer connection = trackerClient.getConnection();
		StorageClient storageClient = new StorageClient(connection, null);
		String[] upload_files = storageClient.upload_file("G:/test/4e447839ee4bfda5d64e4c1cc83637cd_r.jpg", "jpg", null);
		for(String file : upload_files) {
			System.out.println("测试显示:" + file);
		}
	}
	
	@Test
	public void testPath() throws FileNotFoundException, IOException, MyException {
		String path = this.getClass().getResource("/").getPath();
		System.out.println("显示路劲:" + path);
		String conf = "classpath:resource/client.conf";
		//FastDFSClient client = new FastDFSClient(conf);
		//if (conf.contains("classpath:")) {
			conf.replace("classpath:", this.getClass().getResource("/").getPath());
		//}
		System.out.println("打印修改后的路径:" + conf);
		System.out.println("打印replace测试:" + "classpath:resource/client.conf".replace("classpath", this.getClass().getResource("/").getPath()));
	}
}
