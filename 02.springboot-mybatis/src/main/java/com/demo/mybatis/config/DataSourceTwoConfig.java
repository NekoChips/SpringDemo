package com.demo.mybatis.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author NekoChips
 * @description 数据源二的配置类
 * @date 2020/3/25
 */
@Configuration
@MapperScan(basePackages = DataSourceTwoConfig.PACKAGE, sqlSessionFactoryRef = "db2SqlSessionFactory")
public class DataSourceTwoConfig {

    public static final String PACKAGE = "com.demo.mybatis.mapper2";

    public static final String MAPPER_LOCATION = "classpath:mapper/*.xml";

    @Bean
    @ConfigurationProperties("spring.datasource.druid.db2")
    public DataSource dataSourceTwo() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("dataSourceTwo") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);

//        sqlSessionFactory.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources(DataSourceOneConfig.MAPPER_LOCATION));
        return sqlSessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager db2transactionManager() {
        return new DataSourceTransactionManager(dataSourceTwo());
    }

    
}
