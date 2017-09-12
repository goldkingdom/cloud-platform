package cn.xj.common.model;

import java.io.Serializable;
import java.util.Map;

public class PageInfo extends BaseInfo implements Serializable {

    private Integer currentPage;
    private Integer itemsPerPage;
    private Integer totalItems;
    private Integer totalPages;
    private String countColumn;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public String getCountColumn() {
        return countColumn;
    }

    public void setCountColumn(String countColumn) {
        this.countColumn = countColumn;
    }

    public PageInfo() {
    }

    public PageInfo(Integer currentPage, Integer itemsPerPage) {
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
    }

    public PageInfo(StringBuffer instruction, Integer currentPage, Integer itemsPerPage) {
        super(instruction);
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
    }

    public PageInfo(StringBuffer instruction, Class clazz, Integer currentPage, Integer itemsPerPage) {
        super(instruction, clazz);
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
    }

    public PageInfo(StringBuffer instruction, Map params, Integer currentPage, Integer itemsPerPage) {
        super(instruction, params);
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
    }

    public PageInfo(StringBuffer instruction, Map<String, Object> params, Class clazz, Integer currentPage, Integer itemsPerPage) {
        super(instruction, params, clazz);
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
    }

    public PageInfo(Integer currentPage, Integer itemsPerPage, String countColumn) {
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
        this.countColumn = countColumn;
    }

    public PageInfo(StringBuffer instruction, Integer currentPage, Integer itemsPerPage, String countColumn) {
        super(instruction);
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
        this.countColumn = countColumn;
    }

    public PageInfo(StringBuffer instruction, Class clazz, Integer currentPage, Integer itemsPerPage, String countColumn) {
        super(instruction, clazz);
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
        this.countColumn = countColumn;
    }

    public PageInfo(StringBuffer instruction, Map params, Integer currentPage, Integer itemsPerPage, String countColumn) {
        super(instruction, params);
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
        this.countColumn = countColumn;
    }

    public PageInfo(StringBuffer instruction, Map<String, Object> params, Class clazz, Integer currentPage, Integer itemsPerPage, String countColumn) {
        super(instruction, params, clazz);
        this.currentPage = currentPage;
        this.itemsPerPage = itemsPerPage;
        this.countColumn = countColumn;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "currentPage=" + currentPage +
                ", itemsPerPage=" + itemsPerPage +
                ", totalItems=" + totalItems +
                ", totalPages=" + totalPages +
                ", countColumn='" + countColumn + '\'' +
                '}';
    }

}
