package org.wctf.tool.murder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quartz")
public class QuartzContoller {

	@RequestMapping("/job-define")
	public String jobdefine() {
		return "quartz/job-define";
	}
	
	@RequestMapping("/running-status")
	public String runningstatus() {
		return "quartz/running-status";
	}
	
}
