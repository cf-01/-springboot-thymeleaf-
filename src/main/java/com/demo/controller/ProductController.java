package com.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.demo.dto.ProductDTO;
import com.demo.dto.SearchDTO;
import com.demo.pojo.Area;
import com.demo.pojo.Product;
import com.demo.pojo.User;
import com.demo.service.AreaService;
import com.demo.service.ProductService;
import com.demo.service.UserService;
import com.demo.vo.ProductVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private UserService userService;

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "product_add";
    }

    @GetMapping(value = "/page/{pageNum}")
    public String getProductPage(@PathVariable("pageNum") Integer pageNum, Model model,HttpServletRequest servletRequest) {
        // 获取当前登录用户id
        User user1 = (User) servletRequest.getSession().getAttribute("user");
        //获取商品的分页信息
        PageInfo<Product> page = productService.getProductPage(pageNum,user1.getId());
        List<Product> list = page.getList();
        // 获取该用户自己的商品
        List<Product> list1 = productService.getProductByUserId(user1.getId());
        list.addAll(list1);
        // 按照id排序
        Collections.sort(list, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getId()-o2.getId();
            }
        });
        List<ProductVO> productVOList;
        productVOList = BeanUtil.copyToList(list, ProductVO.class);
        for (ProductVO productVO : productVOList) {
            Area area = areaService.getAreaById(productVO.getAreaId());
            User user = userService.getUserById(productVO.getUserId());
            if (area == null || user == null) {
                continue;
            }
            productVO.setAreaName(area.getAreaName());
            productVO.setUserName(user.getUsername());
        }
        PageInfo<ProductVO> pageVO = new PageInfo<>(productVOList);
        pageVO.setPageNum(page.getPageNum());
        pageVO.setPages(page.getPages());
        pageVO.setTotal(page.getTotal());
        pageVO.setPageSize(page.getPageSize());
        //将分页数据共享到请求域中
        model.addAttribute("page", pageVO);
        model.addAttribute("user",user1);
        //跳转到employee_list.html
        return "product_list";
    }


    @GetMapping(value = "/")
    public String getAllProduct(Model model, HttpServletRequest servletRequest) {
        //查询所有的员工信息
        User user = (User) servletRequest.getSession().getAttribute("user");
        List<Product> list = productService.getAllProduct(user.getId());
        //将员工信息在请求域中共享
        model.addAttribute("list", list);
        //跳转到employee_list.html
        return "product_list";
    }


    @PostMapping(value = "/addProduct")
    @CrossOrigin
    @ResponseBody
    public Map<String, Object> addProduct(@RequestBody ProductDTO productDTO, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        String[] features = productDTO.getFeatures();
        // 处理features
        String feature = "";
        String otherDescription = productDTO.getOtherDescription();
        for (String s : features) {
            feature += s + ",";
        }
        if (otherDescription != null) {
            feature += otherDescription;
        } else {
            feature = feature.substring(0, feature.length() - 1);
        }
        Product product = new Product();
        // 复制productDTO的属性到product
        BeanUtil.copyProperties(productDTO, product);
        product.setDescription(feature);
        // 获取当前用户的id
        User user = (User) request.getSession().getAttribute("user");
        product.setUserId(user.getId());
        int i = productService.addProduct(product);
        if (i > 0) {
            result.put("code", 200);
            result.put("msg", "添加成功");
        } else {
            result.put("code", 500);
            result.put("msg", "添加失败");
        }
        return result;
    }

    @GetMapping(value = "/{id}")
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        Product product = productService.getProductById(id);
        Area area = areaService.getAreaById(product.getAreaId());
        ProductVO productVO = new ProductVO();
        BeanUtil.copyProperties(product, productVO);
        productVO.setAreaName(area.getAreaName());
        List<Area> allArea = areaService.getAllArea();
        model.addAttribute("product", productVO);
        model.addAttribute("areas", allArea);
        return "product_update";
    }

    @GetMapping(value = "/toDetail/{id}")
    public String toDetail(@PathVariable("id") Integer id, Model model) {
        Product product = productService.getProductById(id);
        Area area = areaService.getAreaById(product.getAreaId());
        ProductVO productVO = new ProductVO();
        BeanUtil.copyProperties(product, productVO);
        productVO.setAreaName(area.getAreaName());
        productVO.setUserName(userService.getUserById(productVO.getUserId()).getUsername());
        List<Area> allArea = areaService.getAllArea();
        model.addAttribute("product", productVO);
        model.addAttribute("areas", allArea);
        return "product_detial";
    }


    // 批量删除接口
    @DeleteMapping("/batchDelete")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> batchDelete(@RequestBody List<Integer> ids) {
        Map<String,Object> result = new HashMap<>();
        int a = productService.batchDelete(ids);
        if (a>0) {
            result.put("code",200);
            result.put("msg","批量删除成功");
        }else {
            result.put("code",500);
            result.put("msg","批量删除失败");
        }
        return result;
    }

    // 模糊搜索接口
    @PostMapping("/search")
    @CrossOrigin
    @ResponseBody
    public Map<String,Object> search(@RequestBody SearchDTO searchDTO,HttpServletRequest servletRequest) {
        // 获取当前登录用户id
        User user1 = (User) servletRequest.getSession().getAttribute("user");
        //获取商品的分页信息
        Map<String,Object> result = new HashMap<>();
        PageInfo<Product> page = productService.search(searchDTO);
        List<Product> list = page.getList();
        List<ProductVO> productVOList = new ArrayList<>();
        productVOList = BeanUtil.copyToList(list, ProductVO.class);
        for (ProductVO productVO : productVOList) {
            Area area = areaService.getAreaById(productVO.getAreaId());
            User user = userService.getUserById(productVO.getUserId());
            if (area == null || user == null) {
                continue;
            }
            productVO.setAreaName(area.getAreaName());
            productVO.setUserName(user.getUsername());
        }
        PageInfo<ProductVO> pageVO = new PageInfo<>(productVOList,5);
        pageVO.setPageNum(page.getPageNum());
        pageVO.setPages(page.getPages());
        pageVO.setTotal(page.getTotal());
        pageVO.setPageSize(page.getPageSize());
        //将分页数据共享到请求域中
        result.put("data",pageVO);
        result.put("user",user1);
        return result;
    }


    @PutMapping(value = "/updateProduct")
    @ResponseBody
    @CrossOrigin
    public Map<String, Object> updateProduct(@RequestBody Product product,HttpServletRequest servletRequest) {
        User user = (User) servletRequest.getSession().getAttribute("user");
        product.setUserId(user.getId());
        int i = productService.updateProduct(product);
        Map<String, Object> result = new HashMap<>();
        if (i > 0) {
            result.put("code", 200);
            result.put("msg", "修改成功");
        } else {
            result.put("code", 500);
            result.put("msg", "修改失败");
        }
        return result;
    }

    @DeleteMapping(value = "/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        int delete = productService.delete(id);
        if (delete > 0) {
            return "redirect:/product/page/1";
        } else {
            System.out.println("删除失败");
            return "redirect:/product/page/1";
        }
    }

}
