package demo.controller.apis;

import demo.dso.ApiCodes;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Mapping;

/**
 * @author noear 2021/1/13 created
 */
@Component(tag = "api")
public class API_0 {
    @Mapping
    public void def() {
        throw ApiCodes.CODE_11;
    }
}
