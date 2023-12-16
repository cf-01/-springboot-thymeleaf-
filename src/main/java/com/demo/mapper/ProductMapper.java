package com.demo.mapper;

import com.demo.dto.SearchDTO;
import com.demo.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> getAllProduct(Integer userId);

    int addProduct(Product product);

    Product getProductById(Integer id);

    int updateProduct(Product product);

    int delete(Integer id);

    int batchDelete(List<Integer> ids);

    List<Product> search(SearchDTO searchDTO);

    List<Product> getProductByUserId(Integer id);
}
