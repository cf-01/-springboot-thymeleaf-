package com.demo.mapper;

import com.demo.pojo.Area;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AreaMapper {
    List<Area> getAllArea();


    @Select("select id,area_name areaName from area where id = #{areaId}")
    Area getAreaById(@Param("areaId") Integer areaId);
}
