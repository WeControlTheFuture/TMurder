package org.wctf.tool.murder;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.wctf.tool.murder.model.col.JobDefine;

@SpringBootApplication

public class MongoTest implements InitializingBean {

	@Autowired
	protected MongoTemplate mongoTemplate;

	public void afterPropertiesSet() {
		BulkOperations ops = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, JobDefine.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MongoTest.class, args);

	}
}
