package demo.controller;

import demo.protocol.HelloService;
import org.noear.mlog.Logger;
import org.noear.mlog.utils.Tags;
import org.noear.nami.NamiAttachment;
import org.noear.nami.annotation.NamiClient;
import org.noear.solon.Utils;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.cloud.CloudClient;
import org.noear.solon.cloud.annotation.CloudConfig;
import org.noear.solon.cloud.model.Event;

/**
 * 这是Solon的控制器（基于Solon Bean 容器运行）；可以跳过
 *
 * @author noear 2020/12/28 created
 */
@Controller
public class TestController {
    static Logger logger = Logger.get(TestController.class);

    @CloudConfig("water_cache_header")
    String water_cache_header;

    //这是本地的
    @Inject
    HelloService helloService;

    //这是远程的
    @NamiClient
    HelloService helloService2;

    @Mapping("/test")
    public String home(String msg) throws Exception {
        helloService.hello();

        NamiAttachment.current().headerSet("test","12");
        helloService2.hello();

        logger.info("我是好人：（");
        logger.info("我不是：{} \n\n {}", "坏人",12);
        logger.info(Tags.tag2("打卡"), "我是谁？");

        if (Utils.isNotEmpty(msg)) {
            Event event = new Event("test.hello", "cloud-test2-" + msg);

            CloudClient.event().publish(event);
            return "OK: *" + CloudClient.trace().getTraceId() + "-" + water_cache_header;
        } else {
            return "NO: " + helloService2.hello();
        }
    }
}
