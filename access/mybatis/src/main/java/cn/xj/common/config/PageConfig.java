package cn.xj.common.config;

import cn.xj.common.model.DynamicDataSource;
import cn.xj.common.sql.service.PageService;
import cn.xj.common.sql.service.impl.MysqlPageServiceImpl;
import cn.xj.common.sql.service.impl.OrclPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Welink on 2017/4/26.
 */
@Configuration
@ComponentScan("cn.xj.common")
public class PageConfig {

    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Bean
    public PageService pageService() {
        if ("com.mysql.jdbc.Driver".equals(dynamicDataSource.getDriverClassName())) {
            return new MysqlPageServiceImpl();
        } else if ("oracle.jdbc.driver.OracleDriver".equals(dynamicDataSource.getDriverClassName())) {
            return new OrclPageServiceImpl();
        } else {
            return null;
        }
    }

}
