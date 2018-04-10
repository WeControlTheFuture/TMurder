package org.wctf.tool.murder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.wctf.tool.murder.model.col.JobDefine;

@Service
public class QuartzService {

	@Autowired
	protected MongoTemplate mongoTemplate;

	public void saveJob(JobDefine jobDefine) {
		mongoTemplate.save(jobDefine);
	}
}
