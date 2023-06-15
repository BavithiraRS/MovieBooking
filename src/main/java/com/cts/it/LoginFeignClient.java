package com.cts.it;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.it.model.Login;


@FeignClient("login")
public interface LoginFeignClient {
	
	@PostMapping("login")
	public ResponseEntity<Login> registerUser(@RequestBody Login login);

}
