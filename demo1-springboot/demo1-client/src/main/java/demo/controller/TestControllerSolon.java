package demo.controller;

import org.noear.nami.annotation.NamiClient;
import org.noear.solon.Utils;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.water.WaterClient;
import demo.protocol.HelloService;
import org.noear.water.annotation.Water;

/**
 * 这是Solon的控制器（基于Solon Bean 容器运行）；可以跳过
 * */
@Controller
public class TestControllerSolon {
    //这是本地的
    @Inject
    HelloService helloService;

    //这是远程的
    @Water
    HelloService helloService2;

    @Mapping("/test")
    public String home(String msg) throws Exception {
        helloService.hello();
        helloService2.hello();

        if (Utils.isNotEmpty(msg)) {
            WaterClient.Message.sendMessage("test.hello", "test-"+msg);
            return "OK: *" + WaterClient.waterTraceId();
        }else{
            return "NO" + helloService2.hello();
        }
    }
}
