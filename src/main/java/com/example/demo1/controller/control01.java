package com.example.demo1.controller;

import com.example.demo1.bean.JdbcUtils;
import com.example.demo1.mapper.AgeCntMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author :
 * @description :
 * @DateTime : 2023-02-16 00:41
 * @Version 1.0
 */
@Controller
public class control01 {

    @Autowired
    public JdbcTemplate jdbcTemplate ;

    @RequestMapping("/insert")
    public void getParam(
            @RequestParam(value = "id" ) int id ,
            @RequestParam(value = "names" ) String names ,
            @RequestParam(value = "score" ) String score
    ) throws SQLException {
        System.out.println(id+names+score);
        //将获取的数据  使用 jdbc 工具类 将数据写入数据库
//        add_jdbc(id,names,score);

        // 使用集成 JdbcTemplate 将数据写入数据库
        add_jdbcTemp(id,names,score);
    }

    public  void add_jdbc(int id , String names,String score) throws SQLException {

        Connection connection = JdbcUtils.getConnection();
        String sql = "insert into mytest values(?,?,?)";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setObject(1,id);
        ps.setObject(2,names);
        ps.setObject(3,score);
        ps.executeUpdate();
        JdbcUtils.close(ps,connection);
    }

    public  void add_jdbcTemp(int id , String names,String score) throws SQLException {

        String sql = "insert into mytest values(?,?,?)";
        //jdbcTemplate.update(sql,id,names,score); 参数1 sql语句 可变形参对占位符赋值
        jdbcTemplate.update(sql,id,names,score);
        //jdbcTemplate.update(sql,id,names,score); 参数1 sql语句 参数2输出条数 可变形参对占位符赋值
//        jdbcTemplate.update(sql,Integer.class, id,names,score);
    }

}
