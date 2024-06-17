package com.hospital.response;

import com.hospital.entity.CheckItem;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealInfoResponse {
    /**套餐编号*/private Integer smId;
    /**套餐名称*/private String name;
    /**套餐类型（1：男士餐套；0：女士套餐）*/private Integer type;
    /**套餐价格*/private Integer price;
    List<CheckItem> checkItems=new ArrayList<>();
}
