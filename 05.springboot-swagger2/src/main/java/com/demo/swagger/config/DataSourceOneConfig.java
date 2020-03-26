package com.demo.swagger.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author NekoChips
 * @description 数据源一的配置类
 * @date 2020/3/25
 */
@Configuration
@MapperScan(basePackages = DataSourceOneConfig.PACKAGE, sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DataSourceOneConfig {

    public static final String PACKAGE = "com.demo.swagger.mapper1";

    // 指定 mapper 文件位置
    public static final String MAPPER_LOCATION = "classpath:mapper/*.xml";

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.druid.db1")
    public DataSource dataSourceOne() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("dataSourceOne") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);

        // 不使用 Mybatis 的 xml 方式的 sql 可不用配置资源位置
//        sqlSessionFactory.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources(DataSourceOneConfig.MAPPER_LOCATION));
        return sqlSessionFactory.getObject();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager db1transactionManager() {
        return new DataSourceTransactionManager(dataSourceOne());
    }


}
