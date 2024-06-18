package com.hospital.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hospital.entity.Cireport;
import com.hospital.entity.OverallResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportInfoResponse {
    private String userId;
    private Integer orderId;
    private Integer hpId;
    private String hpName;
    @JsonFormat( pattern = "yyyy-MM-dd" )
    private Date reportDate;
   private List<CireportInfo> reports=new ArrayList<>();
   private List<OverallResult> overallResults=new ArrayList<>();

}
