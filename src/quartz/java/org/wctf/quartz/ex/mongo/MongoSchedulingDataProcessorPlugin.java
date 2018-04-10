package org.wctf.quartz.ex.mongo;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.plugins.SchedulerPluginWithUserTransactionSupport;
import org.quartz.spi.ClassLoadHelper;

public class MongoSchedulingDataProcessorPlugin extends SchedulerPluginWithUserTransactionSupport {
	protected ClassLoadHelper classLoadHelper = null;
	protected Scheduler scheduler;

	@Override
	public void initialize(String name, Scheduler scheduler, ClassLoadHelper schedulerFactoryClassLoadHelper)
			throws SchedulerException {
		super.initialize(name, scheduler);
		this.scheduler = scheduler;
		this.classLoadHelper = schedulerFactoryClassLoadHelper;
		MongoJobChanged mongoJobChanged = new MongoJobChanged(this);
	}

	public void reScheduleJob(JobDetail jobDetail){
		try {
			scheduler.addJob(jobDetail, true);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
