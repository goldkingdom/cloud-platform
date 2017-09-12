package cn.xj.common.model;

import java.io.Serializable;

public class Result extends ResultTemplate implements Serializable {

    public Result() {
        super();
    }

    public Result(String version, String method, Object response) {
        super(version, method, response);
    }

}
