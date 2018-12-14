package io.github.generator;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(IdGeneratorZKConfiguration.IdGeneratorZookeeperProperties.class)
@Slf4j
public class IdGeneratorZKConfiguration {


    @Bean
    public IdGeneratorZKFactory initGeneratorFactory(IdGeneratorZookeeperProperties properties) {
        return IdGeneratorZKFactory.builder()
                .zkAddress(properties.zookeeperServers)
                .connectTimeout(properties.connectTimeout)
                .sessionTimeout(properties.sessionTimeout)
                .root(properties.root)
                .build();
    }


    @Setter
    @Getter
    //配置的默认前缀
    /**
     * 总结：
       保证datacenterId 和workId的组合不要出现 相同值就行

     * 1.改进sj的id生成
     * 也可考虑使用jdbc-sharding中关于idkey的生成 使用本机的ip之和作为workId的初始值
     *  datacenterId作为配置传入，然后workId直接用本机IP取和计算（同一个机房C类地址 IP取和不存在相同的数值）
     *
     * 2.此处我们不配置这些 通过ZK的特性直接create节点（/#{root}/#{index}） 做%运算计算得出workId和datacenterId
     */
    @ConfigurationProperties(prefix = "id.generator.zookeeper")
    public static class IdGeneratorZookeeperProperties {

        private String zookeeperServers;

        private String root = "/idGenerator";

        private int sessionTimeout = 5000;//默认5S

        private long connectTimeout = 5000;//默认5S
    }


}
