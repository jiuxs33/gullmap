package com.gullmap.framework.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.gullmap.framework.solr.SolrPool;

@Configuration
public class CommonConfig {

	@Resource
	private Environment environment;
	
	@Bean(name="solrpool")
	SolrPool solrPool(){
		return new SolrPool(environment.getRequiredProperty("spring.data.solr.host"));
	}
}
