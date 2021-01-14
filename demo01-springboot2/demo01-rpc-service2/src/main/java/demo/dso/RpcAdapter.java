package demo.dso;

import org.noear.solon.Solon;
import org.noear.solon.SolonApp;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Solon Rpc 适配器（使用 Springboot 体系，为 Springboot 组件，添加 Solon Rpc 支持）
 *
 * @author noear 2021/1/14 created
 */
@Component
public class RpcAdapter implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        start(Solon.global());
    }

    private void start(SolonApp app) {
        app.add("/rpc/", HelloServiceRemoteImp.class, true);
    }
}
