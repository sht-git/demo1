package com.example.demo1.service.impl;

import com.example.demo1.bean.myBean;
import com.example.demo1.mapper.AgeCntMapper;
import com.example.demo1.service.AgeCntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author :
 * @description :
 * @DateTime : 2023-01-27 16:24
 * @Version 1.0
 */
@Service   //注明为service
public class AgeCntServiceImpl implements AgeCntService {

    @Autowired
    public AgeCntMapper ageCntMapper ;

    @Override
    public int getMapperAge(int dt) {
        return ageCntMapper.getTotalAge(dt);
    }

    @Override
    public Map getAgeByName(int dt) {
        // 获取mapper层数据
        List<Map> ageByName = ageCntMapper.getAgeByName(dt);

        //创建hashmap 用于存放结果
        HashMap<String, BigDecimal> result = new HashMap<>();

        for (Map map : ageByName) {
            result.put( (String) map.get("name"),(BigDecimal) map.get("age")) ;
        }
        return result;
    }

    @Override
    public List<myBean> getMytest(int dt) {
        List<myBean> mytest = ageCntMapper.getMytest(dt);

        return mytest;
    }
}
