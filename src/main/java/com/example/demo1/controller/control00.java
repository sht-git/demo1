package com.example.demo1.controller;

import com.example.demo1.bean.JdbcUtils;
import com.example.demo1.mapper.AgeCntMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;


/**
 * @Author :
 * @description :
 * @DateTime : 2023-02-15 00:27
 * @Version 1.0
 */
@Controller
//@RestController
public class control00 {

    @Autowired
    public AgeCntMapper ageCntMapper ;

    // TODO 1 测试从 url 获取数据 并插入数据库
    @RequestMapping("/insertMethod")
    @ResponseBody
    public void insert(@RequestParam(value="id") int id ,
                       @RequestParam(value = "names") String names,
                       @RequestParam(value = "score") String score){
        //调用mapper方法 将数据写入数据库
        int i = ageCntMapper.insertMethod(id, names, score);
        System.out.println(i);
    }

    // TODO 2 从此处发出请求将页面跳转到 testform表单页
    @RequestMapping("/mytestform")
//    @ResponseBody
    public String test(){
        System.out.println("页面跳转到testform表单页");
        return "testform";
    }
    //测试从 form表单获取数据插入数据库
//    @ResponseBody
    @RequestMapping("/testAdd")   // 与 testform 表单映射   <form action="/testAdd" method="post">
    public String insertAdd(HttpServletRequest httpServletRequest){ //TODO 使用 HttpServletRequest 获取表单数据
        String id = httpServletRequest.getParameter("id");
        String user_name = httpServletRequest.getParameter("user_name");
        String password = httpServletRequest.getParameter("password");
        String season = httpServletRequest.getParameter("season");
        String team = null ;
//        team = httpServletRequest.getParameter("team");
        String interesting = httpServletRequest.getParameter("interesting");
        //获取所有参数集合
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            if (entry.getKey().equals("team")) {
                team = Arrays.toString(entry.getValue());
            }
        }
        //调用mapper方法 将数据写入数据库
        boolean b = ageCntMapper.insertForm(0, user_name, password, season, team, interesting);
        System.out.println(b+":跳转到写入成功界面");
        //跳转到写入成功界面
        return "insert";
    }

    @RequestMapping("/industry")
    public String getindustry(){
        return "industry" ;
    }

}

