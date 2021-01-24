package testapi.controller;

import org.noear.solon.Utils;
import org.noear.solon.cloud.CloudClient;
import org.noear.solon.cloud.CloudEventHandler;
import org.noear.solon.cloud.CloudLogger;
import org.noear.solon.cloud.annotation.CloudEvent;
import org.noear.solon.cloud.model.Event;
import org.noear.water.utils.Datetime;

@CloudEvent("test.hello")
public class TestMessage implements CloudEventHandler {

    CloudLogger logger = CloudLogger.get(TestController.class);

    @Override
    public boolean handler(Event event) throws Throwable {
        logger.info("有消息过来了");

        Event event1 = new Event("test.hello", event.content());
        event1.key(Utils.guid());
        event1.scheduled(Datetime.Now().addSecond(10).getFulltime());

        CloudClient.event().push(event1);
        return true;
    }
}
