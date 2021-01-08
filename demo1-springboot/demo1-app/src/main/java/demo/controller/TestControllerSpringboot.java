package demo.controller;

import demo.protocol.HelloService;
import org.noear.nami.annotation.NamiClient;
import org.noear.solon.Utils;
import org.noear.solon.annotation.Inject;
import org.noear.water.WaterClient;
import org.noear.water.annotation.Water;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 这是Solon的控制器（基于Solon Bean 容器运行）；可以跳过
 *
 * @author noear 2020/12/28 created
 */
@RestController
public class TestControllerSpringboot {
    @Water("water/water_cache_header")
    String water_cache_header;

    //这是本地的
    @Resource
    HelloService helloService;

    //这是远程的
    @Water
    HelloService helloService2;

    @RequestMapping("/test2")
    public String home(String msg) throws Exception {
        helloService.hello();
        helloService2.hello();

        if (Utils.isNotEmpty(msg)) {
            WaterClient.Message.sendMessage("test.hello", "test2-" + msg);
            return "OK: *" + WaterClient.waterTraceId() + "-" + water_cache_header;
        } else {
            return "NO: " + helloService2.hello();
        }
    }
}
