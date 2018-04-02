package org.wctf.tool.murder.model.col;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jobdefine")
public class JobDefine {
	private Schedule schedule;

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	private static class Schedule {
		Job job;
		List<Trigger> trigger;
	}

	private static class Job {
		String name;
		String group;
		String description;
		boolean durable;
		boolean recover;
		JobDataMap jobDataMap;
	}

	private static class JobDataMap {
		List<Entry> entry;
	}

	private static class Entry {
		String key;
		String value;
	}

	private static class Trigger {
		Cron cron;
		DateInterval dateInterval;
		Simple simple;
	}

	private static class TriggerBase {
		String name;
		String group;
		String jobName;
		String jobGroup;
		String description;
		String startTime;
		String endTime;
	}

	private static class Cron extends TriggerBase {
		String timeZone = "";
		String misfireInstruction;
		String cronExpression;
		JobDataMap jobDataMap;
	}

	private static class DateInterval extends TriggerBase {
		String repeatInterval;
		String repeatIntervalUnit;
		JobDataMap jobDataMap;
	}

	private static class Simple extends TriggerBase {
		String repeatInterval;
		String repeatCount;
		JobDataMap jobDataMap;

	}
}
