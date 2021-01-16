package demo.controller;

import org.noear.solon.extend.cloud.CloudConfigHandler;
import org.noear.solon.extend.cloud.annotation.CloudConfig;
import org.noear.solon.extend.cloud.model.Config;

/**
 * 配置订阅（获取配置的时实刷新）
 *
 * @author noear 2021/1/6 created
 */
@CloudConfig("water/water_cache_header")
public class TestConfigHandler implements CloudConfigHandler {

    @Override
    public void handler(Config config) {
        System.out.println(config.value);
    }
}
