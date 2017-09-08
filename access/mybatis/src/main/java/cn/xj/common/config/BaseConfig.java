package cn.xj.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Welink on 2017/4/26.
 */
@Component
@ConfigurationProperties(prefix = "baseConfig")
public class BaseConfig {

    private String beanPath;

    public String getBeanPath() {
        return beanPath;
    }

    public void setBeanPath(String beanPath) {
        this.beanPath = beanPath;
    }

}
