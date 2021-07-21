package kr.co.ensmart.frameworkdemo.test;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.Resource;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/*
 * 커맨드라인 명령어로 암호화/복호화 할 경우 다음 명령을 실행한다.
 * java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="plain text" password="encryption key" algorithm=PBEWithMD5AndDES
 * java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI input="encrypted password" password="encryption key" algorithm=PBEWithMD5AndDES
 */
@SpringBootTest
@Slf4j
class ConfigurationEncryptorTest {
	@Resource(name="jasyptStringEncryptor")
	StringEncryptor configurationEncryptor;

	static final String TEST_PASSWORD = "x2commerce123!";

	@Test
	public void encryptTest() {
		String encryptedPassword = configurationEncryptor.encrypt(TEST_PASSWORD);
//		String encryptedPassword = "8tK0jEaNLkl7ieAYJmCw8BJmIjJb/cTVloOnm7KD4ipZJDVZXMqyDD9b7JcIDYWH";
		log.info("encrypted password is : " + encryptedPassword);

		
		String decryptedPassword = configurationEncryptor.decrypt(encryptedPassword);
		log.info("decryptedPassword password is : " + decryptedPassword);
		
		assertThat(decryptedPassword).isEqualTo(TEST_PASSWORD);
	}
}
