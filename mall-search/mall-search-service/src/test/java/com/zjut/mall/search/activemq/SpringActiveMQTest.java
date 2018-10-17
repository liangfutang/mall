package com.zjut.mall.search.activemq;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringActiveMQTest {

	/**
	 * 启动的时候需要将System.in.read()注释掉，否则会导致程序卡在这里(编译的时候卡在这了)
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSpringActiveMq() throws IOException {
		/*
		 * ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
		 * "classpath:spring/applicationContext-activemq.xml"); System.in.read();
		 */
	}
}
