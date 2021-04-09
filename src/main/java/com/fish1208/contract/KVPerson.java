package com.fish1208.contract;

import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.*;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple4;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class KVPerson extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506110106000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166356004b6a6040805190810160405280600b81526020017f74785f6b76706572736f6e0000000000000000000000000000000000000000008152506040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018060200180602001848103845285818151815260200191508051906020019080838360005b8381101561013957808201518184015260208101905061011e565b50505050905090810190601f1680156101665780820380516001836020036101000a031916815260200191505b50848103835260028152602001807f69640000000000000000000000000000000000000000000000000000000000008152506020018481038252600c8152602001807f6e616d652c6167652c7365780000000000000000000000000000000000000000815250602001945050505050602060405180830381600087803b1580156101ef57600080fd5b505af1158015610203573d6000803e3d6000fd5b505050506040513d602081101561021957600080fd5b8101908080519060200190929190505050506110b88061023a6000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063693ec85e14610051578063b3092e25146101b1575b600080fd5b34801561005d57600080fd5b506100b8600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506102c4565b60405180851515151581526020018060200184815260200180602001838103835286818151815260200191508051906020019080838360005b8381101561010c5780820151818401526020810190506100f1565b50505050905090810190601f1680156101395780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b83811015610172578082015181840152602081019050610157565b50505050905090810190601f16801561019f5780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b3480156101bd57600080fd5b506102ae600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506108c5565b6040518082815260200191505060405180910390f35b600060606000606060008060006060600060606000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040805190810160405280600b81526020017f74785f6b76706572736f6e0000000000000000000000000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b838110156103b557808201518184015260208101905061039a565b50505050905090810190601f1680156103e25780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b15801561040157600080fd5b505af1158015610415573d6000803e3d6000fd5b505050506040513d602081101561042b57600080fd5b81019080805190602001909291905050509550600094508573ffffffffffffffffffffffffffffffffffffffff1663693ec85e8c6040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b838110156104ca5780820151818401526020810190506104af565b50505050905090810190601f1680156104f75780820380516001836020036101000a031916815260200191505b50925050506040805180830381600087803b15801561051557600080fd5b505af1158015610529573d6000803e3d6000fd5b505050506040513d604081101561053f57600080fd5b810190808051906020019092919080519060200190929190505050809550819650505084156108ac578373ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260048152602001807f6e616d6500000000000000000000000000000000000000000000000000000000815250602001915050600060405180830381600087803b15801561060857600080fd5b505af115801561061c573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f82011682018060405250602081101561064657600080fd5b81019080805164010000000081111561065e57600080fd5b8281019050602081018481111561067457600080fd5b815185600182028301116401000000008211171561069157600080fd5b505092919050505092508373ffffffffffffffffffffffffffffffffffffffff1663fda69fae6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260038152602001807f6167650000000000000000000000000000000000000000000000000000000000815250602001915050602060405180830381600087803b15801561073b57600080fd5b505af115801561074f573d6000803e3d6000fd5b505050506040513d602081101561076557600080fd5b810190808051906020019092919050505091508373ffffffffffffffffffffffffffffffffffffffff16639c981fcb6040518163ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825260038152602001807f7365780000000000000000000000000000000000000000000000000000000000815250602001915050600060405180830381600087803b15801561081857600080fd5b505af115801561082c573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f82011682018060405250602081101561085657600080fd5b81019080805164010000000081111561086e57600080fd5b8281019050602081018481111561088457600080fd5b81518560018202830111640100000000821117156108a157600080fd5b505092919050505090505b8483838399509950995099505050505050509193509193565b6000806000806000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f23f63c96040805190810160405280600b81526020017f74785f6b76706572736f6e0000000000000000000000000000000000000000008152506040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001828103825283818151815260200191508051906020019080838360005b838110156109a957808201518184015260208101905061098e565b50505050905090810190601f1680156109d65780820380516001836020036101000a031916815260200191505b5092505050602060405180830381600087803b1580156109f557600080fd5b505af1158015610a09573d6000803e3d6000fd5b505050506040513d6020811015610a1f57600080fd5b810190808051906020019092919050505092508273ffffffffffffffffffffffffffffffffffffffff166313db93466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610a9657600080fd5b505af1158015610aaa573d6000803e3d6000fd5b505050506040513d6020811015610ac057600080fd5b810190808051906020019092919050505091508173ffffffffffffffffffffffffffffffffffffffff1663e942b516896040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260028152602001807f6964000000000000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610b93578082015181840152602081019050610b78565b50505050905090810190601f168015610bc05780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610be057600080fd5b505af1158015610bf4573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b516886040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260048152602001807f6e616d6500000000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610cb8578082015181840152602081019050610c9d565b50505050905090810190601f168015610ce55780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610d0557600080fd5b505af1158015610d19573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff16632ef8ba74876040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018080602001838152602001828103825260038152602001807f616765000000000000000000000000000000000000000000000000000000000081525060200192505050600060405180830381600087803b158015610dc557600080fd5b","505af1158015610dd9573d6000803e3d6000fd5b505050508173ffffffffffffffffffffffffffffffffffffffff1663e942b516866040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808060200180602001838103835260038152602001807f7365780000000000000000000000000000000000000000000000000000000000815250602001838103825284818151815260200191508051906020019080838360005b83811015610e9d578082015181840152602081019050610e82565b50505050905090810190601f168015610eca5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b158015610eea57600080fd5b505af1158015610efe573d6000803e3d6000fd5b505050508273ffffffffffffffffffffffffffffffffffffffff1663a815ff1589846040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180806020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825284818151815260200191508051906020019080838360005b83811015610fbd578082015181840152602081019050610fa2565b50505050905090810190601f168015610fea5780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b15801561100a57600080fd5b505af115801561101e573d6000803e3d6000fd5b505050506040513d602081101561103457600080fd5b810190808051906020019092919050505090507fb103249d88cd818b10c5cd6889874103a7699c5834cb078d8f35925dca8a62d6816040518082815260200191505060405180910390a18093505050509493505050505600a165627a7a72305820f370a11a764b9a5ed6bb33d0ecf1028cf6876572bde619d78be29fffc3c65f7a0029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"id\",\"type\":\"string\"}],\"name\":\"get\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"int256\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"id\",\"type\":\"string\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"age\",\"type\":\"int256\"},{\"name\":\"sex\",\"type\":\"string\"}],\"name\":\"set\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"count\",\"type\":\"int256\"}],\"name\":\"SetResult\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_GET = "get";

    public static final String FUNC_SET = "set";

    public static final Event SETRESULT_EVENT = new Event("SetResult", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
    ;

    @Deprecated
    protected KVPerson(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected KVPerson(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected KVPerson(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected KVPerson(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static String getBinary() {
        return BINARY;
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<Tuple4<Boolean, String, BigInteger, String>> get(String id) {
        final Function function = new Function(FUNC_GET, 
                Arrays.<Type>asList(new Utf8String(id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple4<Boolean, String, BigInteger, String>>(
                new Callable<Tuple4<Boolean, String, BigInteger, String>>() {
                    @Override
                    public Tuple4<Boolean, String, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<Boolean, String, BigInteger, String>(
                                (Boolean) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> set(String id, String name, BigInteger age, String sex) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new Utf8String(id),
                new Utf8String(name),
                new Int256(age),
                new Utf8String(sex)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void set(String id, String name, BigInteger age, String sex, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new Utf8String(id),
                new Utf8String(name),
                new Int256(age),
                new Utf8String(sex)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setSeq(String id, String name, BigInteger age, String sex) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new Utf8String(id),
                new Utf8String(name),
                new Int256(age),
                new Utf8String(sex)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple4<String, String, BigInteger, String> getSetInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Int256>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple4<String, String, BigInteger, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue(), 
                (String) results.get(3).getValue()
                );
    }

    public Tuple1<BigInteger> getSetOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_SET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public List<SetResultEventResponse> getSetResultEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(SETRESULT_EVENT, transactionReceipt);
        ArrayList<SetResultEventResponse> responses = new ArrayList<SetResultEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SetResultEventResponse typedResponse = new SetResultEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerSetResultEventLogFilter(String fromBlock, String toBlock, List<String> otherTopics, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(SETRESULT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void registerSetResultEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(SETRESULT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static KVPerson load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new KVPerson(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static KVPerson load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new KVPerson(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static KVPerson load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new KVPerson(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static KVPerson load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new KVPerson(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<KVPerson> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(KVPerson.class, web3j, credentials, contractGasProvider, getBinary(), "");
    }

    public static RemoteCall<KVPerson> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(KVPerson.class, web3j, transactionManager, contractGasProvider, getBinary(), "");
    }

    @Deprecated
    public static RemoteCall<KVPerson> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(KVPerson.class, web3j, credentials, gasPrice, gasLimit, getBinary(), "");
    }

    @Deprecated
    public static RemoteCall<KVPerson> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(KVPerson.class, web3j, transactionManager, gasPrice, gasLimit, getBinary(), "");
    }

    public static class SetResultEventResponse {
        public Log log;

        public BigInteger count;
    }
}
