package demo;

import org.noear.solon.cloud.integration.springboot.EnableCloud;
import org.noear.solon.extend.springboot.EnableSolon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author noear 2020/12/28 created
 */
@EnableCloud
@EnableSolon
@SpringBootApplication
public class ClientApp {
    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class, args);
    }
}
