package com.hospital.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Payment)实体类
 *
 * @author xiaohu
 * @since 2024-06-21 16:11:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment  {
private Integer opId;
private Integer odId;
private String pyId;
private Integer state;
private BigDecimal price;
}


