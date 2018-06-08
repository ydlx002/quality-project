package com.gxjtkyy.quality.domain.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gxjtkyy on 2017/5/30.
 */
public class PageResultVO<T extends Serializable> implements Serializable {
    private int currentPage = 1;

    private int pageSize = 10;

    private int totalElements;

    private List<T> content = new ArrayList<T>();

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        if(pageSize > 0){
            int totalPages = totalElements / pageSize;
            return totalPages == 0?1:totalPages;
        }else {
            return 1;
        }
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public PageResultVO(){

    }
    public PageResultVO(List<T> content, int currentPage, int pageSize, int totalElements){
        this.content = content;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
    }
}
