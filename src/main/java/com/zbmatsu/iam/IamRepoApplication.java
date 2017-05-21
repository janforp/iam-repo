package com.zbmatsu.iam;

import com.zbmatsu.iam.common.PropertiesBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;


@SpringBootApplication
@EnableConfigurationProperties({PropertiesBean.class})
@ServletComponentScan
//启动数据库
@EnableCassandraRepositories
public class IamRepoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IamRepoApplication.class, args);
	}
}