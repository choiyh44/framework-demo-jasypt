package kr.co.ensmart.frameworkdemo.test;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class StringEncryptorTest {
	private static final String PASSWORD = "local";

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
	    System.out.println(encrypt("X2BASE#"));
	    System.out.println(encrypt("secret-encryption-needed"));
	    
	}
}
