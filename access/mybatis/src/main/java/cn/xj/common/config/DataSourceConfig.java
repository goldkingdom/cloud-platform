package cn.xj.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by Welink on 2017/4/26.
 */
@Configuration
@ComponentScan("cn.xj.common")
@MapperScan(basePackages = {"cn.xj.common.sql.mapper", "cn.xj.project.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {

    @Autowired
    private BaseConfig baseConfig;

    @Bean
    @ConfigurationProperties(prefix = "dataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
            bean.setMapperLocations(resolver.getResources("classpath:cn/xj/*/mapper/*.xml"));
            if (baseConfig == null || baseConfig.getBeanPath() == null) {
                bean.setTypeAliasesPackage("cn.xj.common.model");
            } else {
                bean.setTypeAliasesPackage("cn.xj.common.model," + baseConfig.getBeanPath());
            }
        } catch (Exception e) {
            throw new RuntimeException("Mybatis SqlSessionFactory init Error: " + e.getMessage());
        }
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
