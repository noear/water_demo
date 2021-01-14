package demo.dso;

import org.noear.solon.Solon;
import org.noear.solon.SolonApp;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author noear 2021/1/14 created
 */
@Component
public class RpcInitialization implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        start(Solon.global());
    }

    private void start(SolonApp app) {
        app.add("/rpc/", HelloServiceRemoteImp.class, true);
    }
}
