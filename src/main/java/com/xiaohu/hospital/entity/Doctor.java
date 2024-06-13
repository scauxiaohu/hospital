package com.xiaohu.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Doctor)实体类
 *
 * @author xiaohu
 * @since 2024-06-13 12:37:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor  {
/**医生编号*/private Integer docId;
/**医生编码*/private String docCode;
/**真实姓名*/private String realName;
/**密码*/private String password;
/**性别*/private Integer sex;
/**所属科室（1：检验科；2：内科；3：外科）*/private Integer deptno;
}


