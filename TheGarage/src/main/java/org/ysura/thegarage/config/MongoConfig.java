/**
 * 
 */
package org.ysura.thegarage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;

/**
 * @author mnaouali
 *
 */
@Configuration
@EnableMongoRepositories(Constant.REPOSITORIES_PACKAGE)
@ComponentScan(basePackages={Constant.REPOSITORIES_PACKAGE, Constant.SERVICES_PACKAGE})
public class MongoConfig{
	
	@Autowired
	private Mongo mongo;	
	/*
     * Factory bean that creates the com.mongodb.Mongo instance
     */
	@Bean
     public MongoClientFactoryBean mongo() {
          MongoClientFactoryBean factoryBean  = new MongoClientFactoryBean();
          factoryBean.setHost(Constant.DATABASE_HOST);
          return factoryBean ;
     }
		
	@Bean
	public MongoTemplate mongoTemplate() throws Exception{
	    return new MongoTemplate(mongo, Constant.DATABASE_NAME);
	}

}
