package com.hospital.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Users)实体类
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users  {
/**用户编号（手机号码）*/private String userId;
/**密码*/private String password;
/**真实姓名*/private String realName;
/**用户性别（1：男；0女）*/private Integer sex;
/**身份证号*/private String identityCard;
/**出生日期*/private Date birthday;
/**用户类型（1：普通用户；2：东软内部员工；3：其他）*/private Integer userType;
}


