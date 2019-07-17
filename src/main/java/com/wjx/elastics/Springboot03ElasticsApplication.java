package com.wjx.elastics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot默认支持两种技术来和elasticsearch交互；
 * 1、Jest（默认不生效）
 *    需要导入jest的工具包（io.searchbox.client.JestClient）
 * 2、SpringData ElasticSearch【ES版本有可能不合适】
 *    版本适配说明：https://github.com/spring-projects/spring-data-elasticsearch
 *
 *    1）、Client 节点信息clusterNodes; clusterName
 *    2）、ElasticsearchTemplate 操作 elasticSearch
 *    3）、编写一个ElasticsearchRepository的子接口来操作elasticsearch
 *
 *    两种用法：https://github.com/spring-projects/spring-data-elasticsearch
 *
 *
 */
@SpringBootApplication
public class Springboot03ElasticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot03ElasticsApplication.class, args);
    }

}
