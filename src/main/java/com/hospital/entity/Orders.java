package com.hospital.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Orders)实体类
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders  {
/**订单编号*/private Integer orderId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
/**预约日期*/private Date orderDate;
/**客户编号*/private String userId;
/**所属医院编号*/private Integer hpId;
/**所属套餐编号*/private Integer smId;
/**订单状态（1：未归档；2：已归档）*/private Integer state;
}


