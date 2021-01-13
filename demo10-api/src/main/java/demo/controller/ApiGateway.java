package demo.controller;

import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.Aop;
import org.noear.solon.core.handle.Gateway;

/**
 * @author noear 2021/1/13 created
 */
@Mapping("/api/v1/**")
public class ApiGateway extends Gateway {
    @Override
    protected void register() {
        Aop.beanForeach(bw -> {
            if ("api".equals(bw.tag())) {
                add(bw);
            }
        });
    }
}
