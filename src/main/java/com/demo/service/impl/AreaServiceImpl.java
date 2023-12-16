package com.demo.service.impl;

import com.demo.mapper.AreaMapper;
import com.demo.pojo.Area;
import com.demo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;
    @Override
    public List<Area> getAllArea() {
        return areaMapper.getAllArea();
    }

    @Override
    public Area getAreaById(Integer areaId) {
        return areaMapper.getAreaById(areaId);
    }
}
