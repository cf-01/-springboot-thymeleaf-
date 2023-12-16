package com.demo.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product {

    private Integer id;
    private String name;
    private Integer areaId;
    private String specification;
    private String description;
    private String price;
    private Integer userId;
    private Date createTime;
    private Integer isPublic;
    private String img;
}
