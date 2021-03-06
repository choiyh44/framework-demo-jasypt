# framework-demo-jasypt

## jasypt 적용방법

- pom.xml 파일에 jasypt 디펜던시를 추가한다.: [참조 블로그](https://velog.io/@sixhustle/Jasypt)
```
<!-- https://mvnrepository.com/artifact/com.github.ulisesbocchio/jasypt-spring-boot-starter -->
<dependency>
  <groupId>com.github.ulisesbocchio</groupId>
  <artifactId>jasypt-spring-boot-starter</artifactId>
  <version>3.0.2</version>
</dependency>
```
- 필요한 jasypt 설정을 application.yml 에 등록한다.

- jasypt 설정클래스를 Bean으로 생성한다.: 설정클래스 작성하는 방법보다는 -Djasypt.encryptor.password=password로 비번받고 기타사항은 application.yml 에 설정하는 방식이 좀더 안전해 보인다.
```
@Configuration
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
```

- 암호화 대상 문자열을 암호화한다.: jasypt-1.9.3.jar 파일을 사용한다.
```
java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="secret-encryption-needed" password="local" algorithm="PBEWITHHMACSHA512ANDAES_256" saltGeneratorClassName="org.jasypt.salt.RandomSaltGenerator" ivGeneratorClassName="org.jasypt.iv.RandomIvGenerator" stringOutputType="base64"
```

- 암호문을 사용하여 application.yml파일을 작성한다.: ENC() 함수를 사용

```
    username: x2commerce
    password: ENC(QFz9f4+gbzomjR2XsrLExDCl9MPUeVf9izMK8u8Z+3wPTaJaI1J5QDHubJ3xdu6W)
```

