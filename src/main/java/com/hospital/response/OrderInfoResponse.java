package com.hospital.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hospital.entity.Hospital;
import com.hospital.entity.SetMeal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfoResponse {

    /**订单编号*/private Integer orderId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    /**预约日期*/private Date orderDate;
    /**客户编号*/private String userId;
    /**所属医院编号*/private Integer hpId;
    /**所属套餐编号*/private Integer smId;
    /**订单状态（1：未归档；2：已归档）*/private Integer state;
    Hospital hospital;
    SetMeal setMeal;
}
