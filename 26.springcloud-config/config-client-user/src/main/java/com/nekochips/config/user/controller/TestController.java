package com.nekochips.config.user.controller;

import com.nekochips.config.user.props.DataSourceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Neko
 * @description 测试Controller
 * @date 2021/9/21
 */
@RestController
@RequestMapping("/test")
public class TestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private DataSourceProperties properties;

	@RequestMapping("url")
	public String getDataSourceUrl() {
		return properties.getUrl();
	}

	@RequestMapping("username")
	public String getUsername() {
		return properties.getUsername();
	}

	@RequestMapping("password")
	public String getPassword() {
		return properties.getPassword();
	}
}
