package com.jmy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Configuration
@ConfigurationProperties("fly.redis")
public class RedisConfig {

    // 连接节点信息List<String>
    private List<String> nodes;
    // 最大连接数200
    private Integer maxTotal;
    // 最大空闲8
    private Integer maxIdle;
    // 最小空闲3
    private Integer minIdle;

    // 初始化方法
    @Bean
    public JedisCluster shardedJedisPool() {
        Set<HostAndPort> set = new HashSet();
        for (String node : nodes) {
            String host = node.split(":")[0];
            int port = Integer.parseInt(node.split(":")[1]);
            HostAndPort hp = new HostAndPort(host,port);
            set.add(hp);
        }
        // 连接池的属性对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxTotal);
        config.setMinIdle(minIdle);
        return new JedisCluster(set,config);
    }


}
