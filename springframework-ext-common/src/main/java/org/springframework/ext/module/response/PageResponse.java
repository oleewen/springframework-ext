package org.springframework.ext.module.response;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> extends Response<PageModule<T>> {
}
