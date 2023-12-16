package com.demo.vo;

import com.demo.pojo.Product;
import lombok.Data;

@Data
public class ProductVO extends Product {

    private String areaName;
    private String userName;
}
