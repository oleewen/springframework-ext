package org.springframework.ext.module.request;

import lombok.Data;

/**
 * Request默认实现
 *
 * @author only 2012-2-29
 */
@Data
public class PageRequest extends Request {
    private int pageNum = 1;
    private int pageSize = 10;

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

    public int getStart() {
        return (getPageNum() - 1) * getPageSize();
    }

    public int getMax() {
        return getPageSize();
    }
}
