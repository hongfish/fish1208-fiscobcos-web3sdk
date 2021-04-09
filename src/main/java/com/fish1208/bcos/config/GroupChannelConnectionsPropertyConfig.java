package com.fish1208.bcos.config;

import org.fisco.bcos.channel.handler.ChannelConnections;
import org.fisco.bcos.channel.handler.GroupChannelConnectionsConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "group-channel-connections-config")
public class GroupChannelConnectionsPropertyConfig {

    List<ChannelConnections> allChannelConnections = new ArrayList<>();
    private Resource caCert;
    private Resource sslCert;
    private Resource sslKey;
    private Resource gmCaCert;
    private Resource gmEnSslCert;
    private Resource gmEnSslKey;
    private Resource gmSslCert;
    private Resource gmSslKey;

    @Bean
    public GroupChannelConnectionsConfig getGroupChannelConnections() {
        GroupChannelConnectionsConfig groupChannelConnectionsConfig =
                new GroupChannelConnectionsConfig();
        groupChannelConnectionsConfig.setCaCert(caCert);
        groupChannelConnectionsConfig.setSslCert(sslCert);
        groupChannelConnectionsConfig.setSslKey(sslKey);
        //gm
        groupChannelConnectionsConfig.setGmCaCert(gmCaCert);
        groupChannelConnectionsConfig.setGmEnSslCert(gmEnSslCert);
        groupChannelConnectionsConfig.setGmEnSslKey(gmEnSslKey);
        groupChannelConnectionsConfig.setGmSslCert(gmSslCert);
        groupChannelConnectionsConfig.setGmSslKey(gmSslKey);
        groupChannelConnectionsConfig.setAllChannelConnections(allChannelConnections);
        return groupChannelConnectionsConfig;
    }

    /**
     * @return the caCert
     */
    public Resource getCaCert() {
        return caCert;
    }

    /**
     * @param caCert the caCert to set
     */
    public void setCaCert(Resource caCert) {
        this.caCert = caCert;
    }

    /**
     * @return the sslCert
     */
    public Resource getSslCert() {
        return sslCert;
    }

    /**
     * @param sslCert the sslCert to set
     */
    public void setSslCert(Resource sslCert) {
        this.sslCert = sslCert;
    }

    /**
     * @return the sslKey
     */
    public Resource getSslKey() {
        return sslKey;
    }

    /**
     * @param sslKey the sslKey to set
     */
    public void setSslKey(Resource sslKey) {
        this.sslKey = sslKey;
    }

    public Resource getGmCaCert() {
        return gmCaCert;
    }

    public void setGmCaCert(Resource gmCaCert) {
        this.gmCaCert = gmCaCert;
    }

    public Resource getGmEnSslCert() {
        return gmEnSslCert;
    }

    public void setGmEnSslCert(Resource gmEnSslCert) {
        this.gmEnSslCert = gmEnSslCert;
    }

    public Resource getGmEnSslKey() {
        return gmEnSslKey;
    }

    public void setGmEnSslKey(Resource gmEnSslKey) {
        this.gmEnSslKey = gmEnSslKey;
    }

    public Resource getGmSslCert() {
        return gmSslCert;
    }

    public void setGmSslCert(Resource gmSslCert) {
        this.gmSslCert = gmSslCert;
    }

    public Resource getGmSslKey() {
        return gmSslKey;
    }

    public void setGmSslKey(Resource gmSslKey) {
        this.gmSslKey = gmSslKey;
    }

    /**
     * @return the allChannelConnections
     */
    public List<ChannelConnections> getAllChannelConnections() {
        return allChannelConnections;
    }

    /**
     * @param allChannelConnections the allChannelConnections to set
     */
    public void setAllChannelConnections(List<ChannelConnections> allChannelConnections) {
        this.allChannelConnections = allChannelConnections;
    }
    
}
