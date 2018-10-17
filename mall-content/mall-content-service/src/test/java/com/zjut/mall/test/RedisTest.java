package com.zjut.mall.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zjut.mall.jedis.JedisClientPool;

import redis.clients.jedis.Jedis;

public class RedisTest {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("10.43.100.36", 6379);
		jedis.auth("123456");
		System.out.println("测试响应:" + jedis.ping());
		jedis.set("name", "chen");
		System.out.println("查找:" + jedis.get("name"));
		jedis.append("name", " love me");
		System.out.println("拼接后的字符串:" + jedis.get("name"));
		jedis.append("ceshi", "jacke");
		System.out.println("测试库中没有，但是拼接的结果:" + jedis.get("ceshi"));
		jedis.del("ceshi");
		System.out.println("测试查找删除后的值:"+ jedis.get("ceshi"));
		System.out.println();
		jedis.mset("key1", "value1", "key2", "value2", "key3", "value3");
		System.out.println("获取:" + jedis.get("key2"));
	}
	
	public void test(String... strings) {
		
	}
	
	@Test
	public void testJedis() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		JedisClientPool jedisClientPool = ac.getBean(JedisClientPool.class);
		String string = jedisClientPool.hget("INDEX_CONTENT", "89");
		System.out.println(string);
		
	}
}
