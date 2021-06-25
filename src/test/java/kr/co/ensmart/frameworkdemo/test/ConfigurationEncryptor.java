package kr.co.ensmart.frameworkdemo.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/*
 * 커맨드라인 명령어로 암호화/복호화 할 경우 다음 명령을 실행한다.
 * java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="plain text" password="encryption key" algorithm=PBEWithMD5AndDES
 * java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI input="encrypted password" password="encryption key" algorithm=PBEWithMD5AndDES
 */
@SpringBootTest
@Slf4j
class ConfigurationEncryptor {
	@Autowired
	StringEncryptor configurationEncryptor;

	static final String TEST_PASSWORD = "x2commerce123!";

	@Test
	public void encryptTest() {
		String encryptedPassword = configurationEncryptor.encrypt(TEST_PASSWORD);
		log.info("encrypted password is : " + encryptedPassword);

		String decryptedPassword = configurationEncryptor.decrypt(encryptedPassword);
		assertThat(decryptedPassword).isEqualTo(TEST_PASSWORD);
	}
}
