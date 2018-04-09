package com.free.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
public class SecurityWebApplicationInitializer
		extends AbstractSecurityWebApplicationInitializer {
	//如果不与spring 或springmvc 一起使用就需要将webSecurityConfig传给超类，会做一下几件事情
	//自动为你的应用程序的每个URL注册 springSecurityFilterChain过滤器,也就是注册filter
	//添加一个ContextLoadListener 用来载入 WebSecurityConfig.(WebSecurityConfig相当于一个web.xml)
//	public SecurityWebApplicationInitializer() {
//		super(WebSecurityConfig.class);
//	}
}
