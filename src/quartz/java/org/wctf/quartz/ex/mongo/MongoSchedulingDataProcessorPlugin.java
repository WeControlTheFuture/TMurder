package org.wctf.quartz.ex.mongo;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.plugins.SchedulerPluginWithUserTransactionSupport;
import org.quartz.spi.ClassLoadHelper;

public class MongoSchedulingDataProcessorPlugin extends SchedulerPluginWithUserTransactionSupport {
	protected ClassLoadHelper classLoadHelper = null;

	@Override
	public void initialize(String name, Scheduler scheduler, ClassLoadHelper schedulerFactoryClassLoadHelper) throws SchedulerException {
		super.initialize(name, scheduler);
		this.classLoadHelper = schedulerFactoryClassLoadHelper;
	}

}
