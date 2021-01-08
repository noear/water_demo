package demo;

import org.noear.water.integration.springboot.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author noear 2020/12/28 created
 */
@EnableWaterClients
@SpringBootApplication
public class ClientApp {
    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class, args);
    }
}
