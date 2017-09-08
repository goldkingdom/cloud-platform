package cn.xj.common.model;

/**
 * Created by Welink on 2017/4/26.
 */
public class Param {

    private String field;
    private Object value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Param() {
    }

    public Param(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Param{" +
                "field='" + field + '\'' +
                ", value=" + value +
                '}';
    }

}
