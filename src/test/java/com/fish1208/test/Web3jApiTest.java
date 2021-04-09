package com.fish1208.test;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameterName;
import org.fisco.bcos.web3j.protocol.core.methods.response.Peers;
import org.fisco.bcos.web3j.protocol.core.methods.response.TotalTransactionCount;
import org.fisco.bcos.web3j.protocol.core.methods.response.Transaction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@Slf4j
public class Web3jApiTest extends BaseTest {

    @Autowired
    private Web3j web3j;

    @Test
    public void testBlock() throws IOException {

        BigInteger blockNumber = web3j.getBlockNumber().send().getBlockNumber();
        log.info("最新区块高度:{}" , blockNumber);

        TotalTransactionCount.TransactionCount transactionCount = web3j.getTotalTransactionCount().send().getTotalTransactionCount();
        log.info("交易总数和区块高度:{}" , JSONUtil.toJsonStr(transactionCount));

        DefaultBlockParameter parameter = DefaultBlockParameterName.LATEST;
        String code = web3j.getCode("0x67b4cfdad4c7d7143eb408cfd25f21f0849e209f", parameter).send().getCode();
        log.info("合约数据:{}" , code);

        BigInteger number = web3j.getPendingTxSize().send().getPendingTxSize();
        log.info("待打包的交易数量:{}" , number);

        List<Transaction> transactionList = web3j.getPendingTransaction().send().getPendingTransactions();
        log.info("待打包的交易信息:{}" , JSONUtil.toJsonStr(transactionList));

    }

    @Test
    public void testInfo() throws IOException {

        List<String> groupList = web3j.getGroupList().send().getGroupList();
        log.info("节点所属群组的群组ID列表:{}" , JSONUtil.toJsonStr(groupList));

        List<String> peerList = web3j.getGroupPeers().send().getGroupPeers();
        log.info("指定群组内的共识节点和观察节点列表:{}" , JSONUtil.toJsonStr(peerList));

        List<String> nodeIDList = web3j.getNodeIDList().send().getNodeIDList();
        log.info("节点本身和已连接的p2p节点列表:{}" , JSONUtil.toJsonStr(nodeIDList));

        List<Peers.Peer> peers = web3j.getPeers().send().getPeers();
        log.info("已连接的p2p节点信息:{}" , JSONUtil.toJsonStr(peers));

    }




}
