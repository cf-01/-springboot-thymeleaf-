package com.demo.service;

import com.demo.pojo.Area;

import java.util.List;

public interface AreaService {
    List<Area> getAllArea();

    Area getAreaById(Integer areaId);
}
