package com.mall.common.utils;

import java.util.Random;

public class IDUtils {

	public static Long genItemId() {
		long currentTimeMillis = System.currentTimeMillis();
		Random random = new Random();
		int end2 = random.nextInt(99);
		String idStr = currentTimeMillis + String.format("%02d", end2);
		return new Long(idStr);
	}
	
	public static void main(String[] args) {
		System.out.println("显示" + genItemId());
	}
}
