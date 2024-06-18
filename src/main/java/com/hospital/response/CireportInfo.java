package com.hospital.response;

import com.hospital.entity.CidetailedReport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CireportInfo {
    private String ciName;
    private Integer ciId;
    List<CidetailedReport> cidetailedReportList = new ArrayList<>();

}
