package com.mall.pagehelp;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjut.mall.dao.TbItemMapper;
import com.zjut.mall.pojo.TbItem;
import com.zjut.mall.pojo.TbItemExample;

public class PageHelperTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		TbItemMapper itemMapper = context.getBean(TbItemMapper.class);
		PageHelper.startPage(1, 10);
		TbItemExample itemExample = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(itemExample);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		System.out.println("总数：" + pageInfo.getTotal());
		System.out.println("页数：" + pageInfo.getPages());
		System.out.println("list" + pageInfo.getList());
		System.out.println("总条数：" + list.size()); 
	}

}
