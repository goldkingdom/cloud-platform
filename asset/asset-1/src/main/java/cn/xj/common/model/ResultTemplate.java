package cn.xj.common.model;

import org.springframework.stereotype.Component;

/**
 * Created by Welink on 2017/4/27.
 */
@Component
public class ResultTemplate {

    private String version;
    private String method;
    private Object response;
    private String message;
    private String code;
    private boolean flag;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public ResultTemplate() {
        this.version = null;
        this.response = null;
        this.message = null;
        this.code = "0000";
        this.flag = true;
    }

    public ResultTemplate(String version, String method, Object response) {
        this.version = version;
        this.method = method;
        this.response = response;
        this.message = null;
        this.code = "0000";
        this.flag = true;
    }

    @Override
    public String toString() {
        return "ResultTemplate{" +
                "version='" + version + '\'' +
                ", method='" + method + '\'' +
                ", response=" + response +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", flag=" + flag +
                '}';
    }
}
