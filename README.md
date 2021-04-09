基于FiscoBcos的web3sdk实现区块链服务
项目结构
![Image text](https://github.com/hongfish/fish1208-fiscobcos-web3sdk/blob/main/src/main/resources/image/001.png)
contract 应用合约，sol文件
![Image text](https://github.com/hongfish/fish1208-fiscobcos-web3sdk/blob/main/src/main/resources/image/002.png)
fiscobcos-dev   链证书、机构证书、机构私钥、账户
![Image text](https://github.com/hongfish/fish1208-fiscobcos-web3sdk/blob/main/src/main/resources/image/003.png)
application-dev.yml
encrypt-type: # 0：普通， 1：国密
  encrypt-type: 0  #集群使用的加密类型

group-channel-connections-config:
  caCert: classpath:fiscobcos-${spring.profiles.active}/ca.crt  #链证书
  sslCert: classpath:fiscobcos-${spring.profiles.active}/sdk.crt  #机构证书
  sslKey: classpath:fiscobcos-${spring.profiles.active}/sdk.key  #机构私钥
  gmCaCert: classpath:fiscobcos-${spring.profiles.active}/gmca.crt #集群为国密版，则需要配置
  gmEnSslCert: classpath:fiscobcos-${spring.profiles.active}/gmensdk.crt
  gmEnSslKey: classpath:fiscobcos-${spring.profiles.active}/gmensdk.key
  gmSslCert: classpath:fiscobcos-${spring.profiles.active}/gmsdk.crt
  gmSslKey: classpath:fiscobcos-${spring.profiles.active}/gmsdk.key
  all-channel-connections:
    - group-id: 1 # sdk实际连接的群组
      connections-str:
        # 若节点小于v2.3.0版本，查看配置项listen_ip:channel_listen_port
        - 192.168.160.136:20200
        - 192.168.160.136:20201
        #- group-id: 2
        #connections-str:
        #- 192.168.160.130:20200
        #- 192.168.160.130:20201
        #- 192.168.160.135:20202
        #- 192.168.160.135:20203

channel-service:
  group-id: 1 # sdk实际连接的群组
  agency-name: fisco # 机构名称

accounts:
  pem-file: classpath:fiscobcos-${spring.profiles.active}/0x9ff96dcf17f27ddd643c23bc1236733aa92a1f20.pem #账户


contract-address:
  kVPerson: "0x3cc40ecd5000f58c3458fef29b91114bd5e18da3"#合约地址
  
pom.xml
引用fisco-bcos-web3sdk的2.6.1版本
![Image text](https://github.com/hongfish/fish1208-fiscobcos-web3sdk/blob/main/src/main/resources/image/004.png)
拷贝证书
集群的链证书、机构证书、机构私钥复制项目fiscobcos-dev文件下
![Image text](https://github.com/hongfish/fish1208-fiscobcos-web3sdk/blob/main/src/main/resources/image/005.png)
控制台下账户复制项目fiscobcos-dev文件下
![Image text](https://github.com/hongfish/fish1208-fiscobcos-web3sdk/blob/main/src/main/resources/image/006.png)
控制台部署合约
![Image text](https://github.com/hongfish/fish1208-fiscobcos-web3sdk/blob/main/src/main/resources/image/007.png)
deploy KVPerson 
transaction hash: 0x72908963644b7e897bf03d0a9ddb9f76428f5b1684aee89eb251d0adf15bdb75
contract address: 0x3cc40ecd5000f58c3458fef29b91114bd5e18da3

拷贝合约地址
把合约地址复制到项目的application-dev.yml配置文件里，通过合约地址来加载合约，获取合约对象。
![Image text](https://github.com/hongfish/fish1208-fiscobcos-web3sdk/blob/main/src/main/resources/image/008.png)

生成java文件
使用web3sdk api将合约转换成java文件。执行SolidityGeneratorTest的compileSolFilesToJava()，在com.fish1208.temp包下生成java文件。
![Image text](https://github.com/hongfish/fish1208-fiscobcos-web3sdk/blob/main/src/main/resources/image/009.png)



将转换后的java文件复制到项目com.fish1208.contract包里
![Image text](https://github.com/hongfish/fish1208-fiscobcos-web3sdk/blob/main/src/main/resources/image/010.png)

代码开发
AccountConfig.java
通过application-dev.yml配置文件的accounts项，获取签名，Credentials对象

GroupChannelConnectionsPropertyConfig.java
通过application-dev.yml配置文件的group-channel-connections-config项，获取群组连接信息

ServiceConfig.java
通过application-dev.yml配置文件的channel-service项，获取Service对象

Web3jConfig.java
获取web3j对象

ContractConfig.java
通过application-dev.yml配置文件的contract-address得到合约地址，用来加载合约，获取合约对象

PersonController.java
调用合约的set、get方法，进行数据上链、链上数据查询。

项目启动
![Image text](https://github.com/hongfish/fish1208-fiscobcos-web3sdk/blob/main/src/main/resources/image/011.png)

调用接口
执行KVPerson合约set方法
http://127.0.0.1:7022/contract/person/set

请求
POST /contract/person/set HTTP/1.1 
Content-Type: application/json 
{
	"id":"3",
	"name":"成功",
	"age":1000,
	"sex":"女"
}
![Image text](https://github.com/hongfish/fish1208-fiscobcos-web3sdk/blob/main/src/main/resources/image/012.png)

执行KVPerson合约get方法
http://127.0.0.1:7022/contract/person/get?id=3

请求
GET /contract/person/get HTTP/1.1  
id=3
![Image text](https://github.com/hongfish/fish1208-fiscobcos-web3sdk/blob/main/src/main/resources/image/013.png)


Github地址
https://github.com/hongfish/fish1208-fiscobcos-web3sdk

