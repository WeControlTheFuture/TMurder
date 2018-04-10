package org.wctf.tool.murder.common.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

  @Value("${mongodb.db:bixy}")
  private String dbName;
  @Value("${mongodb.uri:mongodb://localhost:27017,localhost:27015,localhost:27016/bixy?replicaSet=TestReplicaSet}")
  private String mongoUri;
  @Value("${mongoDB.client.options.maxConnectionsPerHost:100}")
  private int maxConnectionsPerHost;
  @Value("${mongoDB.client.options.minConnectionsPerHost:1}")
  private int minConnectionsPerHost;
  @Value("${mongoDB.client.options.maxWaitTime:120000}")
  private int maxWaitTime;
  @Value("${mongoDB.client.options.socketTimeout:30000}")
  private int socketTimeout;
  @Value("${mongoDB.client.options.maxConnectionLifeTime:0}")
  private int maxConnectionLifeTime;
  @Value("${mongoDB.client.options.connectTimeout:10000}")
  private int connectTimeout;
  
  
  private MongoClient client;

  @Override
  protected String getDatabaseName() {
    return dbName;
  }

  @Bean
  public Mongo mongo() throws Exception {
    return mongoClient();
  }

  public MongoClient mongoClient() {
    if (client == null) {
      client = new MongoClient(new MongoClientURI(mongoUri));
    }
    return client;
  }

  @Bean
  public MongoDatabase mongoDatabase() {
    return mongoClient().getDatabase(getDatabaseName());
  }

  @Bean
  public MappingMongoConverter mappingMongoConverter() throws Exception {
    DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(this.mongoDbFactory());
    MappingMongoConverter converter =
        new MappingMongoConverter(dbRefResolver, this.mongoMappingContext());
    converter.setTypeMapper(new DefaultMongoTypeMapper(null));

    converter.setCustomConversions(this.customConversions());
    return converter;
  }

  @Bean
  public MongoClientOptions mongoClientOptions() {
    MongoClientOptions options = MongoClientOptions.builder()
        .connectionsPerHost(maxConnectionsPerHost).minConnectionsPerHost(minConnectionsPerHost)
        .maxWaitTime(maxWaitTime).socketTimeout(socketTimeout)
        .maxConnectionLifeTime(maxConnectionLifeTime).connectTimeout(connectTimeout).build();
    return options;
  }
}
