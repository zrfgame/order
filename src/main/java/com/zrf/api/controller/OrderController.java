package com.zrf.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zrf.api.fegin.MemberFegin;

@RestController
public class OrderController {
	@Autowired //注入 resttemplate
	private RestTemplate restTemplate;
	@Autowired
	private MemberFegin memberFegin;
	//调用getMember
	@RequestMapping("/getOrder")
	public String getOrder() {
		String rest=restTemplate.getForObject("http://app-member/getMember", String.class);
		return rest;
	}
	@RequestMapping("/getMember")
	public String getMember() {
		return memberFegin.getMember();
	}
}
