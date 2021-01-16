package demo.controller;

import demo.controller.interceptor.AuthInterceptor;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;

/**
 * @author noear 2021/1/13 created
 */
@Mapping("/cmd/v1/")
@Component
public class CmdGateway extends GatewayBase {
    @Override
    protected void register() {
        addBeans(bw -> "api".equals(bw.tag()));
    }

    @Override
    protected boolean allowPathMerging() {
        return false;
    }

    @Override
    protected String getPathDo(Context c) {
        String tmp = c.param("method");
        if (tmp == null) {
            return null;
        } else {
            return tmp.toUpperCase();
        }
    }
}
