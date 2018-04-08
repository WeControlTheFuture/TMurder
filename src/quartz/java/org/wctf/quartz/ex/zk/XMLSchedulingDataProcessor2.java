package org.wctf.quartz.ex.zk;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.spi.ClassLoadHelper;
import org.quartz.spi.MutableTrigger;
import org.quartz.xml.XMLSchedulingDataProcessor;

public class XMLSchedulingDataProcessor2 extends XMLSchedulingDataProcessor {

	public XMLSchedulingDataProcessor2(ClassLoadHelper clh) throws ParserConfigurationException {
		super(clh);
	}

	@Override
	protected InputStream getInputStream(String fileStr) {
		return new ByteArrayInputStream(fileStr.getBytes());
	}

	@Override
	public void processFileAndScheduleJobs(String fileName, String systemId, Scheduler sched) throws Exception {
		processFile(fileName, systemId);
		executePreProcessCommands(sched);
		removeOverTimeTriggers();
		dealDeleteTriggers(sched, systemId);
		scheduleJobs(sched);
	}

	/**
	 * ɾ������ʱ���Ѿ���Ϊ��ȥ�Ĵ���������ֹ������̨��ô������ٴδ���job����
	 */
	protected void removeOverTimeTriggers() {
		Date now = new Date(System.currentTimeMillis());
		List<MutableTrigger> needRemove = new ArrayList<>();
		for (MutableTrigger trigger : loadedTriggers) {
			if (trigger.getEndTime().before(now))
				needRemove.add(trigger);
		}
		loadedTriggers.removeAll(needRemove);
	}

	/**
	 * ɾ�����������ú���Ҫֹͣ���ڵ��ȵ�trigger
	 * 
	 * @param sched
	 * @param jobName
	 * @throws SchedulerException
	 */
	protected void dealDeleteTriggers(Scheduler sched, String jobName) throws SchedulerException {
		List<? extends Trigger> listTriggers = sched.getTriggersOfJob(new JobKey(jobName, jobName + "Group"));
		List<TriggerKey> runningTriggerNeedToRemove = new ArrayList<>();
		for (Trigger maybeRemove : listTriggers) {
			if (canNotRun(maybeRemove))
				runningTriggerNeedToRemove.add(maybeRemove.getKey());
		}
		sched.unscheduleJobs(runningTriggerNeedToRemove);
	}

	private boolean canNotRun(Trigger trigger) {
		for (MutableTrigger needToRun : loadedTriggers) {
			if (needToRun.getKey().equals(trigger.getKey()))
				return false;
		}
		return true;
	}
}