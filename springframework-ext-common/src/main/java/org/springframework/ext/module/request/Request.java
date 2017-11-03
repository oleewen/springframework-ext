package org.springframework.ext.module.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Request默认实现
 *
 * @author only 2012-2-29
 */
@Data
public class Request {
    @ApiModelProperty(example = "devcon-domain", notes = "调用的系统名")
    private String appName;
}
