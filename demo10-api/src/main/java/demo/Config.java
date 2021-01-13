package demo;

import com.zaxxer.hikari.HikariDataSource;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import javax.sql.DataSource;

/**
 * @author noear 2021/1/13 created
 */
@Configuration
public class Config {
    @Bean("db_water")
    public DataSource water(@Inject("${water.water}") HikariDataSource ds){
        Solon.cfg();
        return ds;
    }
}
