package org.wctf.quartz.ex.mongo;

import org.bson.Document;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.wctf.tool.murder.MurderStart;

import com.mongodb.Block;
import com.mongodb.client.model.changestream.ChangeStreamDocument;

@Component
public class MongoJobChanged implements InitializingBean {
	@Autowired
	protected MongoTemplate mongoTemplate;

	protected MongoSchedulingDataProcessorPlugin mongoSchedulingDataProcessorPlugin;

	public MongoJobChanged(MongoSchedulingDataProcessorPlugin mongoSchedulingDataProcessorPlugin) {
		this.mongoSchedulingDataProcessorPlugin = mongoSchedulingDataProcessorPlugin;
	}

	public void afterPropertiesSet() {
		Block<ChangeStreamDocument<Document>> printBlock = new Block<ChangeStreamDocument<Document>>() {
			@Override
			public void apply(final ChangeStreamDocument<Document> changeStreamDocument) {
				Document doc = changeStreamDocument.getFullDocument();
				System.out.println(changeStreamDocument);
			}
		};
		mongoTemplate.getCollection("jobdefine").watch().forEach(printBlock);
	}

	public static void main(String[] args) {
		SpringApplication.run(MurderStart.class, args);
	}
}
