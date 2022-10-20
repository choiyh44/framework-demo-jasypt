/**
 * 
 */
package kr.co.ensmart.frameworkdemo.test;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author choiyh44
 * @version 1.0
 * @since 2022. 9. 20.
 *
 */
@Slf4j
public class JasyptDecryptTest {
    private static final String PASSWORD = "FNFBNT02-5200001";

    private static StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(PASSWORD);
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


    @Test
    void decrypt() {
        String cypherText = "CDF88AEAA94DE14F4B075330C3CECFDD1FFF9E5EA14956C967F4E1C3FC47CAA2";
        
        StringEncryptor textEncryptor = stringEncryptor();
        String decryptedText = textEncryptor.decrypt(cypherText);
        log.debug("decryptedText: {}", decryptedText);
        
    }
    
}
