package com.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Hospital)实体类
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospital  {
/**医院编号*/private Integer hpId;
/**医院名称*/private String name;
/**医院图片*/private String picture;
/**医院电话*/private String telephone;
/**医院地址*/private String address;
/**营业时间*/private String businessHours;
/**采血截止时间*/private String deadline;
/**预约人数规则*/private String rule;
/**医院状态（1：正常；2：其他）*/private Integer state;
}


