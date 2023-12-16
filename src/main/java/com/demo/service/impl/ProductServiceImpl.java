package com.demo.service.impl;

import com.demo.dto.SearchDTO;
import com.demo.mapper.ProductMapper;
import com.demo.pojo.Product;
import com.demo.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageInfo<Product> getProductPage(Integer pageNum,Integer userId) {
        //开启分页功能
        PageHelper.startPage(pageNum, 4);
        //查询所有的员工信息
        List<Product> list = productMapper.getAllProduct(userId);
        //获取分页相关数据
        PageInfo<Product> page = new PageInfo<>(list, 5);
        return page;
    }

    @Override
    public List<Product> getAllProduct(Integer userId) {
        return productMapper.getAllProduct(userId);
    }

    @Override
    public int addProduct(Product product) {
        return productMapper.addProduct(product);
    }

    @Override
    public Product getProductById(Integer id) {
        Product product = productMapper.getProductById(id);
        return product;
    }

    @Override
    public int updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

    @Override
    public int delete(Integer id) {
        return productMapper.delete(id);
    }

    @Override
    public int batchDelete(List<Integer> ids) {

        return productMapper.batchDelete(ids);
    }

    @Override
    public PageInfo<Product> search(SearchDTO searchDTO) {
        //开启分页功能
        PageHelper.startPage(searchDTO.getPageNum(), 4);
        //查询所有的员工信息
        List<Product> list = productMapper.search(searchDTO);
        //获取分页相关数据
        PageInfo<Product> page = new PageInfo<>(list, 5);
        return page;
    }

    @Override
    public List<Product> getProductByUserId(Integer id) {
        return productMapper.getProductByUserId(id);
    }
}
