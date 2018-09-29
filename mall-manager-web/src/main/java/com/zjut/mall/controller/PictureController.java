package com.zjut.mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.zjut.mall.utils.FastDFSClient;

@RestController
public class PictureController {

	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	@RequestMapping("/pic/upload")
	public String picUpload(MultipartFile uploadFile) {
		String originalFilename = uploadFile.getOriginalFilename();
		System.out.println("打印全名:"+originalFilename);
		String exName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		System.out.println("打印扩展名:"+exName);
		String resultStr;
		Map<String, Object> result = new HashMap<>();
		try {
			FastDFSClient fastDFSClient = new FastDFSClient("E:/StudyWorkSpace/java/mall/mall-manager-web/src/main/resources/resource/client.conf");
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), exName);
			url = IMAGE_SERVER_URL + url;
			result.put("error", 0);
			result.put("url", url);
		}  catch (Exception e) {
			e.printStackTrace();
			result.put("error", 1);
			result.put("message", "图片上传失败");
		}
		resultStr = JSONObject.toJSONString(result);
		
		return resultStr;
	}
}
