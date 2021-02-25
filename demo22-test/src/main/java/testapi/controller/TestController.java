package testapi.controller;

import org.noear.mlog.Logger;
import org.noear.solon.Utils;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.cloud.CloudClient;
import org.noear.solon.cloud.model.Event;

@Controller
public class TestController {
    Logger logger = Logger.get(TestController.class);

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
