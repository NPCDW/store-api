package com.github.npcdw.storeapi.entity.common;

import java.util.List;

public class TableInfo<T> {
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 总页数
     */
    private Integer pageCount;
    /**
     * 记录总数
     */
    private Integer count;
    /**
     * 记录列表
     */
    private List<T> list;

    public TableInfo(Integer currentPage, Integer pageCount, Integer count, List<T> list) {
        this.currentPage = currentPage;
        this.pageCount = pageCount;
        this.count = count;
        this.list = list;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
