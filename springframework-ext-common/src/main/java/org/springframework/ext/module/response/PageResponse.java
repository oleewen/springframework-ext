package org.springframework.ext.module.response;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> extends Response<List<T>> {
    private int total;
    private int size;
    private int pageNum;
    private int pageSize;

    public void setPageNum(int pageNum) {
        if (pageNum > 0) {
            this.pageNum = pageNum;
        }
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public int getPageTotal() {
        int total = getTotal();
        int pageSize = getPageSize();

        int pageTotal = total / pageSize;

        if (total % pageSize > 0) {
            pageTotal += 1;
        }
        return pageTotal;
    }
}
