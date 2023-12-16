package com.demo.dto;

import lombok.Data;


@Data
public class SearchDTO {
    private Integer areaId;
    private String productName;
    private String startDate;
    private String endDate;
    private Integer pageNum;
}
