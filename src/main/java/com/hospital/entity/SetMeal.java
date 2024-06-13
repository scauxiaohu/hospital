package com.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (SetMeal)实体类
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetMeal  {
/**套餐编号*/private Integer smId;
/**套餐名称*/private String name;
/**套餐类型（1：男士餐套；0：女士套餐）*/private Integer type;
/**套餐价格*/private Integer price;
}


