package org.springframework.ext.module.response;

import lombok.Data;
import org.springframework.ext.module.result.Result;

/**
 * 结果对象
 *
 * @param <T> 模板
 * @author only 2017-7-2
 */
@Data
public class Response<T> extends Result<T> {
}
