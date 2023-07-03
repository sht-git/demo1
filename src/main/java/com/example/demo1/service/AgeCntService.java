package com.example.demo1.service;


import com.example.demo1.bean.myBean;

import java.util.List;
import java.util.Map;

/**
 * @Author :
 * @description :
 * @DateTime : 2023-01-27 16:22
 * @Version 1.0
 */
public interface AgeCntService {
    // 获取mapper层数据
    int getMapperAge(int dt) ;

    Map getAgeByName(int dt) ;

    List<myBean> getMytest(int dt) ;

}
