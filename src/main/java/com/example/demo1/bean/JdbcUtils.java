package com.example.demo1.bean;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @className :
 * @description :
 * @Author :
 * @DateTime :2021年06月30日 20 时 21分 ：
 * @Version 1.0
 */
public class JdbcUtils {     //封装工具类
    //创建私有化连接池属性  建意私有
    private static DataSource ds ;
    //私有化构造器
    private JdbcUtils() {}


    // 1 使用静态代码块进行数据连接池的初始化
    static {
        try {
            // 创建属性集
            Properties pro = new Properties();
            //加载 属性集文件
            pro.load(new FileInputStream("D:\\iDea\\demo1\\src\\main\\resources\\jdbc.properties"));
            //加载 初始化 url username password    使用阿里 德鲁伊工具
            ds = DruidDataSourceFactory.createDataSource(pro) ;
        }catch (Exception e){
            throw new RuntimeException("初始化数据连接池错误") ;
        }
    }
    // 2 对外界提供获取的方放
    public static Connection getConnection () throws SQLException {
        // 非空校验
        if (ds != null ) {
            return ds.getConnection() ;
        }
        throw new RuntimeException("初始化错误") ;
    }

    // 3 封装关闭创建连接资源方法
    public  static  void close (Connection conn) {
        try {
            if (conn != null ) {
                conn.close();
            }
        }catch (Exception e ) {
            throw new RuntimeException("关闭资源过程发生错误") ;
        }
    }

    // 4 封装关闭 创建连接 ，获取Statement 对象 资源方法
    public  static  void close (Statement st, Connection conn) {
        try {
            if (st != null) {
                st.close();
            }
        }catch (Exception e ) {
            throw new RuntimeException("关闭资源过程发生错误") ;
        }finally {
           close(conn);    //调用一个参数的关闭方法
        }
    }

    // 5 封装关闭 创建连接 ，获取Statement 对象 ResultSet 资源方法
    public  static  void close (ResultSet re ,Statement st, Connection conn) {
        try {
            if (re != null ){
                re.close();
            }
        }catch (Exception e) {
            throw new RuntimeException("关闭资源过程发生错误") ;
        }finally {
            close(st,conn);  //调用两个参数的关闭方法
        }
    }

    // 6 向上抽取更新工具
    public void updateMethod(String sql,Object...arr) {  // 利用可变形参来传入参数填补不定的占位符
        //调用工具类 加载驱动  JdbcUtils.getConnection()
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //创建获取连接
            conn = JdbcUtils.getConnection();
            //执行sql
            ps = conn.prepareStatement(sql);
            //注入sql
            for (int i = 0; i < arr.length; i++) {
                //利用循环注入数据 ，注意mysql 中的起始索引为 1
                ps.setObject(i+1,arr[i]);
            }
            //执行sql 更新语句
            ps.executeUpdate();
        }catch (Exception e ) {
            e.printStackTrace();
        }finally {
            //关闭资源
            close(ps,conn);
        }
    }
}
