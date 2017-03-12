package com.zbmatsu.iam.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 * Created by Administrator on 2017/3/4.
 */
@Configuration
@EnableCassandraRepositories
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(CassandraConfiguration.class);

    @Value("${cassandra.keyspace-name}")
    private String cassandraKeySpace;

    @Value("${cassandra.port}")
    private String cassandraPort;

    @Value("${cassandra.contactpoints}")
    private String cassandraContactpoints;

    @Override
    protected String getKeyspaceName() {

        logger.info("cassandra key space is &s", cassandraKeySpace);
        return cassandraKeySpace;
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        logger.info("INSIDE cluster()");
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(cassandraContactpoints);
        cluster.setPort(Integer.parseInt(cassandraPort));
        return cluster;
    }

    @Bean
    public CassandraMappingContext cassandraMapping() throws ClassNotFoundException {
        logger.info("INSIDE cassandra mapping");
        return new BasicCassandraMappingContext();
    }
}
