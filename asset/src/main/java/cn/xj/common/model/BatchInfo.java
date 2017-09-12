package cn.xj.common.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BatchInfo extends BaseInfo implements Serializable {

    private List<Map<String, Object>> batchList;
    private List beans;
    private Integer batchNum;
    private String[] fields;

    public List<Map<String, Object>> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<Map<String, Object>> batchList) {
        this.batchList = batchList;
    }

    public List getBeans() {
        return beans;
    }

    public void setBeans(List beans) {
        this.beans = beans;
    }

    public Integer getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(Integer batchNum) {
        this.batchNum = batchNum;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public BatchInfo() {
    }

    public BatchInfo(List<Map<String, Object>> batchList, Integer batchNum) {
        this.batchList = batchList;
        this.batchNum = batchNum;
    }

    public BatchInfo(StringBuffer instruction, List<Map<String, Object>> batchList, Integer batchNum) {
        super(instruction);
        this.batchList = batchList;
        this.batchNum = batchNum;
    }

    public BatchInfo(StringBuffer instruction, Class clazz, List<Map<String, Object>> batchList, Integer batchNum) {
        super(instruction, clazz);
        this.batchList = batchList;
        this.batchNum = batchNum;
    }

    public BatchInfo(StringBuffer instruction, Map params, List<Map<String, Object>> batchList, Integer batchNum) {
        super(instruction, params);
        this.batchList = batchList;
        this.batchNum = batchNum;
    }

    public BatchInfo(StringBuffer instruction, Map<String, Object> params, Class clazz, List<Map<String, Object>> batchList, Integer batchNum) {
        super(instruction, params, clazz);
        this.batchList = batchList;
        this.batchNum = batchNum;
    }

    @Override
    public String toString() {
        return "BatchInfo{" +
                "batchList=" + batchList +
                ", batchNum=" + batchNum +
                '}';
    }

}
