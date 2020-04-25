package com.demo.elastic.config;

import com.demo.elastic.props.ElasticProperties;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NekoChips
 * @description ES 配置类
 * @date 2020/4/22
 */
@Configuration
public class ElasticConfig
{
    @Autowired
    private ElasticProperties elasticProperties;
    
    @Bean
    public RestHighLevelClient highLevelClient() {
        return new RestHighLevelClient(restClientBuilder());
    }

    @Bean
    public RestClientBuilder restClientBuilder()
    {
        return RestClient.builder(makeHttpHost());
    }

    private HttpHost[] makeHttpHost()
    {
        List<HttpHost> httpHosts = new ArrayList<>();
        List<ElasticProperties.EsNode> nodes = elasticProperties.getNodes();
        if (CollectionUtils.isEmpty(nodes)) {
            throw new RuntimeException("nodes must not be null!");
        }
        nodes.forEach(esNode -> httpHosts.add(new HttpHost(esNode.getHost(), esNode.getPort(), elasticProperties.getSchema())));
        return httpHosts.toArray(new HttpHost[nodes.size()]);
    }

}
