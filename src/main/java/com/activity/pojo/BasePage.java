package com.activity.pojo;

/**
 * @author Created by ky.bai on 2018-02-13
 */
public class BasePage {

    private Integer currentPage;

    private Integer pageSize = 15;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
