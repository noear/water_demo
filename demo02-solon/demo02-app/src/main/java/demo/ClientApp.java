package demo;


import org.noear.nami.integration.solon.EnableNamiClient;
import org.noear.solon.Solon;

/**
 * @author noear 2020/12/28 created
 */
@EnableNamiClient
public class ClientApp {
    public static void main(String[] args) {
        Solon.start(ClientApp.class, args);
    }
}
