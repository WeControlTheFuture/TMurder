package org.wctf.tool.murder.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.data.mongo")
public class MongoDbProperties {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String user;
	private String password;
	private String host = "localhost";
	private int port = 27017;
	private String database;

	public String getMongoDbUri() {
		String mongodburi = null;
		if (user == null || password == null) {
			mongodburi = String.format("mongodb://%s:%d/%s", host, port, database);
		} else {
			mongodburi = String.format("mongodb://%s:%s@%s:%d/%s", user, password, host, port, database);
		}
		logger.info("Mongo db uri ................" + mongodburi);
		return mongodburi;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}
}