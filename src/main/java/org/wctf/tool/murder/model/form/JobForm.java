package org.wctf.tool.murder.model.form;

import java.util.Map;

import javax.validation.constraints.NotEmpty;

public class JobForm {
	@NotEmpty(message = "name can't be null")
	private String name;
	@NotEmpty(message = "group can't be null")
	private String group;
	private String description;
	@NotEmpty(message = "job class can't be null")
	private String jobClass;
	private boolean recover;
	private Map<String, String> jobDataMap;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public boolean isRecover() {
		return recover;
	}

	public void setRecover(boolean recover) {
		this.recover = recover;
	}

	public Map<String, String> getJobDataMap() {
		return jobDataMap;
	}

	public void setJobDataMap(Map<String, String> jobDataMap) {
		this.jobDataMap = jobDataMap;
	}

}
