package com.demo.service;

import com.demo.dto.SearchDTO;
import com.demo.pojo.Product;
import com.demo.vo.ProductVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductService {
    PageInfo<Product> getProductPage(Integer pageNum,Integer userId);

    List<Product> getAllProduct(Integer userId);

    int addProduct(Product product);

    Product getProductById(Integer id);

    int updateProduct(Product product);

    int delete(Integer id);

    int batchDelete(List<Integer> ids);

    PageInfo<Product> search(SearchDTO searchDTO);

    List<Product> getProductByUserId(Integer id);
}
