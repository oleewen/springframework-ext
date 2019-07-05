package org.springframework.ext.module.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Request默认实现
 *
 * @author only 2012-2-29
 */
@Data
public class Request implements Serializable {
    private String appName;
}
