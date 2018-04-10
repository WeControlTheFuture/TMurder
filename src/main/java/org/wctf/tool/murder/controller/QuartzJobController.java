package org.wctf.tool.murder.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wctf.tool.murder.model.col.JobDefine;
import org.wctf.tool.murder.model.col.JobDefine.Job;
import org.wctf.tool.murder.model.form.JobForm;
import org.wctf.tool.murder.service.QuartzService;

@RestController
@RequestMapping("quartz")
public class QuartzJobController {

	@Autowired
	private QuartzService quartzService;

	public void createJob(@Valid JobForm jobForm, BindingResult result) {
		if (result.hasErrors())
			return;

		Job job = new Job();
		BeanUtils.copyProperties(jobForm, job);
		JobDefine jobDefine = new JobDefine(job);
		quartzService.saveJob(jobDefine);
	}
}
