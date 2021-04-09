package com.fish1208.bcos.config;

import com.fish1208.bcos.ContractAddress;
import com.fish1208.common.constant.GasConstants;
import com.fish1208.contract.KVPerson;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConfigurationProperties(prefix = "contract-address")
public class ContractConfig {

    private String kVPerson;

    @Autowired
    private Web3j web3j;

    @Autowired
    private Credentials credentials;

    @Bean
    public KVPerson loadTokenERC20(){
        return KVPerson.load(kVPerson, web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
    }

    @Bean
    public ContractAddress setAddress(){
        log.info("kVPerson={}", kVPerson);
        ContractAddress contractAddress = new ContractAddress();
        contractAddress.setKVPerson(kVPerson);
        return contractAddress;
    }

    public String getkVPerson() {
        return kVPerson;
    }

    public void setkVPerson(String kVPerson) {
        this.kVPerson = kVPerson;
    }
}
