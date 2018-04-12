package org.wctf.tool.murder.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wctf.tool.murder.common.model.CtrlResp;
import org.wctf.tool.murder.model.col.JobDefine;
import org.wctf.tool.murder.model.col.JobDefine.Job;
import org.wctf.tool.murder.model.form.JobForm;
import org.wctf.tool.murder.service.QuartzService;

@RestController
@RequestMapping("/quartz")
public class QuartzJobController {

	@Autowired
	private QuartzService quartzService;

	@RequestMapping(value = "/createjob", method = RequestMethod.POST)
	public CtrlResp createJob(@Valid @RequestBody JobForm jobForm, BindingResult result) {
		System.out.println("call................create job");
		System.out.println("jobForm.............." + jobForm.getName());
		CtrlResp ctrlResp = new CtrlResp();
		if (result.hasErrors()) {
			ctrlResp.setSuccess(false);
			return ctrlResp;
		}

		Job job = new Job();
		BeanUtils.copyProperties(jobForm, job);
		job.setJobDataMap(jobForm.getJobData());
		JobDefine jobDefine = new JobDefine(job);
		quartzService.saveJob(jobDefine);
		ctrlResp.setSuccess(true);
		return ctrlResp;
	}

}
