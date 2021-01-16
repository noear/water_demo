package demo.controller;

import demo.controller.interceptor.AuthInterceptor;
import org.noear.snack.ONode;
import org.noear.solon.Utils;
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
    protected void handlePre(Context c) throws Throwable {
        String json = c.body();
        if (Utils.isEmpty(json)) {
            throw ApiCodes.CODE_14;
        } else {
            ONode tmp = ONode.load(json);
            tmp.forEach((k, v) -> {
                c.paramMap().put(k, v.getString());
            });

            if (c.param("method") == null) {
                throw ApiCodes.CODE_14;
            }
        }
    }

    @Override
    protected boolean allowPathMerging() {
        return false;
    }

    @Override
    protected String getPathDo(Context c) {
        return c.param("method").toUpperCase();
    }
}
