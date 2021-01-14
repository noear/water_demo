package demo.controller;

import demo.controller.interceptor.AuthInterceptor;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Mapping;

/**
 * @author noear 2021/1/13 created
 */
@Mapping("/api/v1/**")
@Component
public class ApiGateway extends GatewayBase {
    @Override
    protected void register() {
        before(AuthInterceptor.class);

        loadBean(bw -> "api".equals(bw.tag()));
    }
}
