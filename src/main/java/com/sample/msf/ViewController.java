package com.sample.msf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mobigen.framework.component.Messages;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = { "/" })
public class ViewController {

	@Autowired
	private Messages message;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String hello() {
		return "index";
	}

	@RequestMapping(value = { "/msf" }, method = RequestMethod.GET)
	public String viewMain() {
		return "/apps/app";
	}

	@RequestMapping(value = { "/sample" }, method = RequestMethod.GET)
	public String viewDashboard() {
		return "/apps/sample/sample";
	}



	/*
	* 코드 리뷰 테스트입니다.
	*
	* */
	public void test(){
		System.out.println("test!!!!!");
	}

}
