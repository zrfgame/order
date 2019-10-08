package com.zrf.api.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="APP-MEMBER")
public interface MemberFegin {
	//Fegin 书写方式是以接口方式显示
	@RequestMapping("/getMember")
	public String getMember();
}
