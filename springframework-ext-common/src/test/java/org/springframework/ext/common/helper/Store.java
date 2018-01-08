package org.springframework.ext.common.helper;

import lombok.Data;

import java.util.Date;

/**
 * @author ouliyuan
 * @date 2018-01-04
 */
@Data
public class Store {
    private Date onlineTime;
    private Long status;
}
