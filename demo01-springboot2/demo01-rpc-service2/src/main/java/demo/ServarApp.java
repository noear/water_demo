package demo;

import org.noear.solon.cloud.integration.springboot.EnableCloudClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author noear 2021/1/8 created
 */
@EnableCloudClients
@SpringBootApplication
public class ServarApp {
    public static void main(String[] args) {
        SpringApplication.run(ServarApp.class, args);
    }
}
