package cn.xj.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "userInfo")
public class UserInfo {

    private String name;//要与consul上面一致

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
