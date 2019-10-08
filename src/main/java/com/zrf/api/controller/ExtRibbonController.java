package com.zrf.api.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ExtRibbonController {
	// 可以获取注册中心上的服务列表
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired //注入restTemple
	private RestTemplate restTemplate;
	// 接口的请求总数
	//使用原子类AtomicInteger 解决线程安全问题
	private AtomicInteger a = new AtomicInteger(1);
	
	// 纯手写Ribbon负载均衡
	@RequestMapping("/ribbonMeber")
	public String ribbonMeber() {
		// 获取对应服务器远程调用地址
		String url=getInstances();
		String result = restTemplate.getForObject(url+"/getMember", String.class);
		return result;

	}

	/***
	 * 获取注册中心的真是地址信息
	 * 
	 * @return
	 */
	private String getInstances() {
		List<ServiceInstance> instances = discoveryClient.getInstances("APP-MEMBER");
		if (instances == null || instances.size() <= 0) {
			return null;
		}
		int InstanceSize = instances.size();
		// 请求总数%于服务器总数=下标
		int index = a.get()  % InstanceSize;
		a.getAndIncrement(); 
		return instances.get(index).getUri().toString();

	}
}
