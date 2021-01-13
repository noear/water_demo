package demo.controller.apis;

import demo.controller.ApiBase;
import demo.dso.mapper.WaterMapper;
import demo.model.ConfigModel;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.extend.validation.annotation.NotNull;
import org.noear.weed.annotation.Db;

import java.sql.SQLException;

/**
 * @author noear 2021/1/13 created
 */
@Component(tag = "api")
public class API_config_get extends ApiBase {

    @Db
    WaterMapper waterMapper;

    @NotNull("user_id")
    @Mapping("config/get")
    public ConfigModel exec(long user_id) throws SQLException {
        return waterMapper.getConfig("water", "water");
    }
}
