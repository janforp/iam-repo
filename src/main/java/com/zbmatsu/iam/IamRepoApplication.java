package com.zbmatsu.iam;

import com.zbmatsu.iam.common.PropertiesBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@EnableConfigurationProperties({PropertiesBean.class})
@ServletComponentScan
public class IamRepoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IamRepoApplication.class, args);
	}
}