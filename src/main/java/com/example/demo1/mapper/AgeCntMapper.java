package com.example.demo1.mapper;

import com.example.demo1.bean.myBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author :
 * @description :
 * @DateTime : 2023-01-27 16:19
 * @Version 1.0
 */

// 使用Mapper 注解 或者在 Demon1Application 上使用  @MapperScan(basePackages = "com.example.demo1.mapper") 功能一样
@Mapper
public interface AgeCntMapper {

    @Select("select sum(age) from mysql_test where dt=#{dt}")
    int getTotalAge(int dt);

    //获取多个字段可用 map类型来接收
    @Select("select name,sum(age) as age from mysql_test where dt=#{dt} group by name")
    List<Map> getAgeByName(int dt) ;

    // 返回值使用javabean接收
    @Select("select name,sum(age) as total_age from mysql_test where dt=#{dt} group by name")
    List<myBean> getMytest(int dt);

    //测试 url 获取数据 并插入数据库
    @Insert("insert into mytest (id,names,score) values(#{id},#{names},#{score})")
    int insertMethod(int id,String names ,String score);

    //测试从 form 获取数据并插入数据库
    @Insert("insert into user_info values(#{id},#{user_name},#{password},#{season},#{team},#{interesting})")
    boolean insertForm(int id,String user_name,String password,String season,String team,String interesting);
}
