package com.jmy.config;

import java.net.InetAddress;
import java.util.List;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "fly.es")
public class EsConfig {

    private List<String> nodes;

    public List<String> getNodes() {
        return nodes;
    }
    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }
    @Bean
    public TransportClient transportClient() throws Exception {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);

        for (String node : nodes) {
            String ip = node.split(":")[0];
            int port = Integer.parseInt(node.split(":")[1]);
            InetSocketTransportAddress addres = new InetSocketTransportAddress(
                    InetAddress.getByName(ip), port);
            client.addTransportAddress(addres);
        }

        return client;
    }
}
