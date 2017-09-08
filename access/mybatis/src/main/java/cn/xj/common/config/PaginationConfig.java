package cn.xj.common.config;

import cn.xj.common.model.DynamicDataSource;
import cn.xj.common.sql.service.PaginationService;
import cn.xj.common.sql.service.impl.MysqlPaginationServiceImpl;
import cn.xj.common.sql.service.impl.OrclPaginationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Welink on 2017/4/26.
 */
@Configuration
@ComponentScan("cn.xj.common")
public class PaginationConfig {

    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Bean
    public PaginationService pagerService() {
        if ("com.mysql.jdbc.Driver".equals(dynamicDataSource.getDriverClassName())) {
            return new MysqlPaginationServiceImpl();
        } else if ("oracle.jdbc.driver.OracleDriver".equals(dynamicDataSource.getDriverClassName())) {
            return new OrclPaginationServiceImpl();
        } else {
            return null;
        }
    }

}
