package demo.controller;

import demo.controller.interceptor.AuthInterceptor;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;

/**
 * @author noear 2021/1/13 created
 */
@Mapping("/api/v1/**")
@Component
public class ApiGateway extends GatewayBase {
    @Override
    protected void register() {
        //为网关添加签权处理
        before(AuthInterceptor.class);

        addBeans(bw -> "api".equals(bw.tag()));
    }
}
