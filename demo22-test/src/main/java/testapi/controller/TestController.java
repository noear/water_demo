package testapi.controller;

import org.noear.solon.Utils;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.cloud.CloudClient;
import org.noear.solon.cloud.CloudLogger;
import org.noear.solon.cloud.model.Event;
import org.noear.water.WaterClient;

@Controller
public class TestController {
    CloudLogger logger = CloudLogger.get(TestController.class);

    @Mapping("/")
    public String home(String msg) throws Exception {

        logger.info("有人刷了一下");

        if (Utils.isNotEmpty(msg)) {
            CloudClient.event().publish(new Event("test.hello", msg));
            return "OK: *" + CloudClient.trace().getTraceId();
        } else {
            return "NO";
        }
    }
}
