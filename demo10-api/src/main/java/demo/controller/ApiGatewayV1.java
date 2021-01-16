package demo.controller;

import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Mapping;

/**
 * @author noear 2021/1/13 created
 */
@Mapping("/api/v1/**")
@Component
public class ApiGatewayV1 extends GatewayBase {
    @Override
    protected void register() {
        addBeans(bw -> "api".equals(bw.tag()));
    }
}
