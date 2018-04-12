package org.wctf.tool.murder.model.form;

import java.util.HashMap;
import java.util.List;
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
	private List<Entry> jobDataMap;

	public Map<String, String> getJobData() {

		if (jobDataMap == null)
			return null;
		Map<String, String> result = new HashMap<>();
		jobDataMap.forEach(entry -> {
			if (entry.getKey() != null)
				result.put(entry.getKey(), entry.getValue());
		});
		return result;
	}

	public static class Entry {
		private String key;
		private String value;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

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

	public List<Entry> getJobDataMap() {
		return jobDataMap;
	}

	public void setJobDataMap(List<Entry> jobDataMap) {
		this.jobDataMap = jobDataMap;
	}

}
