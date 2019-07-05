package org.springframework.ext.module.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageModule<T> implements Serializable {
    private List<T> items;
    private Integer total;
    private Integer size;
    private Integer pageNum;
    private Integer pageSize;

    public void setPageNum(Integer pageNum) {
        if (pageNum > 0) {
            this.pageNum = pageNum;
        }
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public Integer getPageTotal() {
        Integer total = getTotal();
        Integer pageSize = getPageSize();

        Integer pageTotal = total / pageSize;

        if (total % pageSize > 0) {
            pageTotal += 1;
        }
        return pageTotal;
    }
}
