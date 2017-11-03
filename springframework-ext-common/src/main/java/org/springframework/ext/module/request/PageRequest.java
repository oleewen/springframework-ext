package org.springframework.ext.module.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageRequest extends Request {
    @ApiModelProperty(example = "1")
    private int pageNum = 1;
    @ApiModelProperty(example = "10")
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
