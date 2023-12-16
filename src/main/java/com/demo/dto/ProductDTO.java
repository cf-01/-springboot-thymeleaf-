package com.demo.dto;

import com.demo.pojo.Product;
import lombok.Data;

@Data
public class ProductDTO extends Product {

    private String otherDescription;

    private String[] features;
}
