package org.springframework.ext.module.request;

import lombok.Data;

/**
 * Request默认实现
 *
 * @author only 2012-2-29
 */
@Data
public class PageRequest extends Request {
    private Integer pageNum = 1;
    private Integer pageSize = 10;

    public void setPageNum(Integer pageNum) {
        if (pageNum != null && pageNum > 0) {
            this.pageNum = pageNum;
        }
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != null && pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public Integer getStart() {
        return (getPageNum() - 1) * getPageSize();
    }

    public Integer getMax() {
        return getPageSize();
    }
}
