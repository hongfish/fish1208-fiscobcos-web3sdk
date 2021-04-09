package com.fish1208.bcos.config;

import com.fish1208.common.constant.ConnectConstants;
import org.fisco.bcos.channel.client.Service;

import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Web3jConfig {

    @Bean
    public Web3j getWeb3j(Service service) throws Exception {
        ChannelEthereumService channelEthereumService = new ChannelEthereumService();
        service.run();
        channelEthereumService.setChannelService(service);
        channelEthereumService.setTimeout(ConnectConstants.TIME_OUT);
        return Web3j.build(channelEthereumService, service.getGroupId());
    }
}
