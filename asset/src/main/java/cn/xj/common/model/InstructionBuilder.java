package cn.xj.common.model;

import java.io.Serializable;

public class InstructionBuilder implements Serializable {

    private String handle; // 句柄
    private Object param; // 参数

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public InstructionBuilder() {
    }

    public InstructionBuilder(String handle, Object param) {
        this.handle = handle;
        this.param = param;
    }

    @Override
    public String toString() {
        return "InstructionBuilder [handle=" + handle + ", param=" + param + "]";
    }

}
