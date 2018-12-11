package io.github.generator;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(ZookeeperGeneratorAutoConfiguration.IdGeneratorZookeeperProperties.class)
@Slf4j
public class ZookeeperGeneratorAutoConfiguration {


    @Bean
    public IdGeneratorFactoryBean idGeneratorFactoryBean(IdGeneratorZookeeperProperties properties) {
        return IdGeneratorFactoryBean.builder()
                .zkAddress(properties.zookeeperServers)
                .connectTimeout(properties.connectTimeout)
                .sessionTimeout(properties.sessionTimeout)
                .root(properties.root)
                .build();
    }


    @Setter
    @Getter
    //配置的默认前缀
    @ConfigurationProperties(prefix = "id.generator.zookeeper")
    public static class IdGeneratorZookeeperProperties {

        private String zookeeperServers;

        private String root = "/idGenerator";

        private int sessionTimeout = 3000;

        private long connectTimeout = 3000;
    }


}
