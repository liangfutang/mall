package com.zjut.mall.search.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 * 
 * @author liangfutang
 *
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		logger.info("进入全局异常的处理中。。。。");
		logger.debug("测试handler类型:" + handler.getClass());
		ex.printStackTrace();
		logger.error("系统异常:" + ex);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "您的电脑有问题，请稍后重试。");
		modelAndView.setViewName("error/exception");
		return modelAndView;
	}

}
