package demo.controller;

import org.noear.snack.ONode;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.core.Aop;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Gateway;
import org.noear.solon.core.handle.Result;

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

    /**
     * 对输出做统一的格式化处理（包括异常格式化）
     * */
    @Override
    public void render(Object obj, Context ctx) throws Throwable {
        if (ctx.getRendered()) {
            return;
        }

        //最多一次渲染
        ctx.setRendered(true);
        ctx.result = obj;


        if (obj == null) {
            obj = Result.succeed();
        }

        if (obj instanceof String) {
            ctx.outputAsJson((String) obj);
        } else {
            if (obj instanceof ONode) {
                ctx.outputAsJson(((ONode) obj).toJson());
            } else {
                //
                // 重点在这块
                //
                if (obj instanceof ApiCode) {
                    ApiCode err = (ApiCode) obj;
                    obj = Result.failure(err.getCode(), ApiCodes.getDescription(err));
                }

                if (obj instanceof Throwable) {
                    Throwable err = (Throwable) obj;
                    obj = Result.failure(err.getMessage());
                }

                if ((obj instanceof Result) == false) {
                    obj = Result.succeed(obj);
                }

                ctx.outputAsJson(ONode.stringify(obj));
            }
        }
    }
}
