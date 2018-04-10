package org.wctf.tool.murder.model.col;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jobdefine")
public class JobDefine {
	@Id
	private String _id;

	private Job job;
	private List<Trigger> trigger;

	public JobDefine(Job job) {
		this.job = job;
		this._id = job.getGroup() + "_" + job.getName();
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;

	}

	public List<Trigger> getTrigger() {
		return trigger;
	}

	public void setTrigger(List<Trigger> trigger) {
		this.trigger = trigger;
	}

	public static class Job {
		private String name;
		private String group;
		private String description;
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

	public static class Trigger {
		private String name;
		private String group;
		private String jobName;
		private String jobGroup;
		private String description;
		private String startTime;
		private String endTime;

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

		public String getJobName() {
			return jobName;
		}

		public void setJobName(String jobName) {
			this.jobName = jobName;
		}

		public String getJobGroup() {
			return jobGroup;
		}

		public void setJobGroup(String jobGroup) {
			this.jobGroup = jobGroup;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

	}

	public static class Cron extends Trigger {
		private String timeZone = "";
		private String misfireInstruction;
		private String cronExpression;
		private Map<String, String> jobDataMap;

		public String getTimeZone() {
			return timeZone;
		}

		public void setTimeZone(String timeZone) {
			this.timeZone = timeZone;
		}

		public String getMisfireInstruction() {
			return misfireInstruction;
		}

		public void setMisfireInstruction(String misfireInstruction) {
			this.misfireInstruction = misfireInstruction;
		}

		public String getCronExpression() {
			return cronExpression;
		}

		public void setCronExpression(String cronExpression) {
			this.cronExpression = cronExpression;
		}

		public Map<String, String> getJobDataMap() {
			return jobDataMap;
		}

		public void setJobDataMap(Map<String, String> jobDataMap) {
			this.jobDataMap = jobDataMap;
		}

	}

	public static class DateInterval extends Trigger {
		private String repeatInterval;
		private String repeatIntervalUnit;
		private Map<String, String> jobDataMap;

		public String getRepeatInterval() {
			return repeatInterval;
		}

		public void setRepeatInterval(String repeatInterval) {
			this.repeatInterval = repeatInterval;
		}

		public String getRepeatIntervalUnit() {
			return repeatIntervalUnit;
		}

		public void setRepeatIntervalUnit(String repeatIntervalUnit) {
			this.repeatIntervalUnit = repeatIntervalUnit;
		}

		public Map<String, String> getJobDataMap() {
			return jobDataMap;
		}

		public void setJobDataMap(Map<String, String> jobDataMap) {
			this.jobDataMap = jobDataMap;
		}

	}

	public static class Simple extends Trigger {
		private String repeatInterval;
		private String repeatCount;
		private Map<String, String> jobDataMap;

		public String getRepeatInterval() {
			return repeatInterval;
		}

		public void setRepeatInterval(String repeatInterval) {
			this.repeatInterval = repeatInterval;
		}

		public String getRepeatCount() {
			return repeatCount;
		}

		public void setRepeatCount(String repeatCount) {
			this.repeatCount = repeatCount;
		}

		public Map<String, String> getJobDataMap() {
			return jobDataMap;
		}

		public void setJobDataMap(Map<String, String> jobDataMap) {
			this.jobDataMap = jobDataMap;
		}

	}
}
