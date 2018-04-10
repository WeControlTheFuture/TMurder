package org.wctf.quartz.ex.mongo;

import org.bson.Document;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.wctf.tool.murder.MurderStart;
import org.wctf.tool.murder.model.col.JobDefine;
import org.wctf.tool.murder.model.col.JobDefine.Job;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.OperationType;

@Component
public class MongoJobChanged implements InitializingBean {
	@Autowired
	protected MongoTemplate mongoTemplate;

	protected MongoSchedulingDataProcessorPlugin mongoSchedulingDataProcessorPlugin;

	protected Block<ChangeStreamDocument<Document>> printBlock = new Block<ChangeStreamDocument<Document>>() {
		@Override
		public void apply(final ChangeStreamDocument<Document> changeStreamDocument) {
			Document doc = changeStreamDocument.getFullDocument();
			OperationType operationType = changeStreamDocument.getOperationType();
			if (operationType.equals(OperationType.REPLACE) || operationType.equals(OperationType.UPDATE)) {
				System.out.println(doc.toJson());
				JobDefine jobDefine = (new Gson()).fromJson(doc.toJson(), JobDefine.class);
				JobDetail jobDetail = getJobDetail(jobDefine);
				mongoSchedulingDataProcessorPlugin.reScheduleJob(jobDetail);

			}
			if (operationType.equals(OperationType.DELETE)) {
				// TODO: mongo change stream
				// 对于删除并没有返回fulldoc，但是会有_id，稍后可以将jobkey改造成_id
			}
		}
	};

	public MongoJobChanged() {

	}

	public MongoJobChanged(MongoSchedulingDataProcessorPlugin mongoSchedulingDataProcessorPlugin) {
		this.mongoSchedulingDataProcessorPlugin = mongoSchedulingDataProcessorPlugin;
	}

	public void afterPropertiesSet() {
		mongoTemplate.getCollection("jobdefine").watch().forEach(printBlock);
	}

	@SuppressWarnings("unchecked")
	private JobDetail getJobDetail(JobDefine jobDefine) {
		JobBuilder jobBuilder = JobBuilder.newJob();
		Job job = jobDefine.getJob();
		jobBuilder.withIdentity(job.getName(), job.getGroup());
		jobBuilder.requestRecovery(job.isRecover());
		jobBuilder.withDescription(job.getDescription());
		try {
			Class<?> cls = Class.forName(job.getJobClass());
			if (!cls.isAssignableFrom(org.quartz.Job.class)) {
				System.out.println("class(" + job.getJobClass() + ") not implements org.quartz.Job interface!");
				return null;
			}
			jobBuilder.ofType((Class<? extends org.quartz.Job>) cls);
		} catch (ClassNotFoundException e) {
			System.out.println("class(" + job.getJobClass() + ") not found!");
			return null;
		}
		if (job.getJobDataMap() != null) {
			jobBuilder.setJobData(new JobDataMap(job.getJobDataMap()));
		}

		jobBuilder.storeDurably();
		return jobBuilder.build();
	}

	private Trigger getTrigger(JobDefine jobDefine) {
		TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
		return triggerBuilder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(MurderStart.class, args);
	}
}
