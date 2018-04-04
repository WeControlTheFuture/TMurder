package org.wctf.tool.murder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/table")
	public String table() {
		return "table";
	}
	
	@RequestMapping("/ui")
	public String ui() {
		return "ui";
	}
	
	@RequestMapping("/form")
	public String form() {
		return "form";
	}
	
	@RequestMapping("/chart")
	public String chart() {
		return "chart";
	}
	
	@RequestMapping("/typography")
	public String typography() {
		return "typography";
	}
	
	@RequestMapping("/gallery")
	public String gallery() {
		return "gallery";
	}
	
	@RequestMapping("/calendar")
	public String calendar() {
		return "calendar";
	}
	
	@RequestMapping("/grid")
	public String grid() {
		return "grid";
	}
	
	@RequestMapping("/icon")
	public String icon() {
		return "icon";
	}
	
	@RequestMapping("/errorExample")
	public String error() {
		return "errorExample";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
