package kr.co.ensmart.frameworkdemo.test;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class StringEncryptorTest {
	private static final String PASSWORD = "x2beeencpasswd";

	private static StringEncryptor stringEncryptor() {
	    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
	    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
	    config.setPassword(PASSWORD);
	    config.setAlgorithm("PBEWithMD5AndDES");
	    config.setKeyObtentionIterations("1000");
	    config.setPoolSize("1");
	    config.setProviderName("SunJCE");
	    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
	    config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
	    config.setStringOutputType("base64");
	    encryptor.setConfig(config);
	    return encryptor;
	}

	private static String encrypt(String text) {
	    StringEncryptor textEncryptor = stringEncryptor();
	    String encryptedText = textEncryptor.encrypt(text);
	    return encryptedText;
	}

	private static String decrypt(String text) {
	    StringEncryptor textEncryptor = stringEncryptor();
	    String decryptedText = textEncryptor.decrypt(text);
	    return decryptedText;
	}

	public static void main(String[] args) {
	    System.out.println(encrypt("x2commerce123!"));
	}
}
