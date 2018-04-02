package org.wctf.tool.murder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

	@RequestMapping("/")
	public String index() {
		System.out.println("call.......");
		return "index";
	}
}
