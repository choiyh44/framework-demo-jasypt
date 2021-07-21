package kr.co.ensmart.frameworkdemo.base.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/*
 * 커맨드라인 명령어로 암호화/복호화 할 경우 다음 명령을 실행한다.
 * java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="secret-encryption-needed" password="local" algorithm="PBEWITHHMACSHA512ANDAES_256" saltGeneratorClassName="org.jasypt.salt.RandomSaltGenerator" ivGeneratorClassName="org.jasypt.iv.RandomIvGenerator" stringOutputType="base64"
 * java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI input="tPTaIXGb/JuKzYzDn0Yba44yrURlH9GsO1vMxPW9mR6kBoNR0CWqTY4vXTuhJkSdEcTn836uapzV0ILlnCV/wQ==" password="local" algorithm="PBEWITHHMACSHA512ANDAES_256" saltGeneratorClassName="org.jasypt.salt.RandomSaltGenerator" ivGeneratorClassName="org.jasypt.iv.RandomIvGenerator" stringOutputType="base64"
 */
//@Configuration
public class JasyptEncryptorConfig {
	@Value("${jasypt.encryptor.password}")
    private String encryptKey;

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(encryptKey);
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
	    config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }	
}
