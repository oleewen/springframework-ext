package org.springframework.ext.module.response;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> extends Response<List<T>> {
    private int total;
    private int size;
    private int pageNum;
    private int pageSize;
}
