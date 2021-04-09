package com.fish1208.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator;
import org.fisco.solc.compiler.CompilationResult;
import org.fisco.solc.compiler.SolidityCompiler;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.fisco.solc.compiler.SolidityCompiler.Options.ABI;
import static org.fisco.solc.compiler.SolidityCompiler.Options.BIN;
import static org.fisco.solc.compiler.SolidityCompiler.Options.INTERFACE;
import static org.fisco.solc.compiler.SolidityCompiler.Options.METADATA;

@Slf4j
public class SolidityGeneratorTest extends BaseTest{

    @Test
    public void compileSolFilesToJava() throws IOException {
        File solFile = new File("D:\\_CodeSource\\java\\fish1208-fiscobcos-web3sdk\\contract\\solidity\\KVPerson.sol");
        SolidityCompiler.Result res = SolidityCompiler.compile(solFile, false, true, ABI, BIN, INTERFACE, METADATA);
        log.info("Out: '{}'" , res.getOutput());
        log.info("Err: '{}'" , res.getErrors());
        CompilationResult result = CompilationResult.parse(res.getOutput());
        log.info("contractname  {}" , solFile.getName());
        String contractname = solFile.getName().split("\\.")[0];
        CompilationResult.ContractMetadata a = result.getContract(solFile.getName().split("\\.")[0]);
        log.info("abi   {}" , a.abi);
        log.info("bin   {}" , a.bin);
        FileUtils.writeStringToFile(new File("src/test/resources/solidity/" + contractname + ".abi"), a.abi);
        FileUtils.writeStringToFile(new File("src/test/resources/solidity/" + contractname + ".bin"), a.bin);
        String binFile;
        String abiFile;
        String tempDirPath = new File("src/test/java/").getAbsolutePath();
        String packageName = "com.fish1208.temp";
        String filename = contractname;
        abiFile = "src/test/resources/solidity/" + filename + ".abi";
        binFile = "src/test/resources/solidity/" + filename + ".bin";
        SolidityFunctionWrapperGenerator.main(Arrays.asList(
                "-a", abiFile,
                "-b", binFile,
                "-p", packageName,
                "-o", tempDirPath
        ).toArray(new String[0]));
    }

}
