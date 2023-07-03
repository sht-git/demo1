package com.example.demo1.controller;

import com.example.demo1.bean.myBean;
import com.example.demo1.service.AgeCntService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.net.httpserver.HttpServerImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @Author :
 * @description :
 * @DateTime : 2023-01-27 16:01
 * @Version 1.0
 */
//@RestController
@Controller   //无法直接返回字符串  可用 RequestController
public class control {
    //TODO 如果使用 thymeleaf  html放到 templates文件夹下即可    返回时省略掉  .html
    @RequestMapping("/test")
    public String getTest(@RequestParam("name") String name ,
                          @RequestParam(value = "age",defaultValue = "20") int age){
        System.out.println("启动");
        return "mysuccess" ;
    }

    @RequestMapping("/test1")
    public String getTest1(@RequestParam("name") String name ,
                          @RequestParam("gender") String gender){
        System.out.println("启动");
        System.out.println("name:" +name +","+"gender"+gender);
        return "form" ;
    }


    @RequestMapping("/form")
    public String getParmas(@RequestParam(value = "user_name", required = false, defaultValue = "hehe") String username){
        System.out.println("启动");

        System.out.println("name:" +username );
        return "form" ;
    }

    @RequestMapping("/href")
    public String getParam(HttpServletRequest httpServletRequest){
        System.out.println("启动");
        System.out.println("name:" +httpServletRequest.getParameter("name") +","+"password:"+httpServletRequest.getParameter("password"));
        return "th" ;
    }

    @RequestMapping("/target")
    public String getTarget(){
        System.out.println("启动");
        return "target" ;
    }

    // TODO 查询数据库数据并返回
    @Autowired
    public AgeCntService ageCntService ;

    @RequestMapping("/getAge")
    @ResponseBody
    public String getAge(@RequestParam(value = "dt", defaultValue = "0") int dt){

        if (dt == 0) {
            dt = getToday();
        }
        int mapperAge = ageCntService.getMapperAge(dt);

        return "{ " +
                "  \"status\": 0, " +
                "  \"info\": \"年龄统计\", " +
                "  \"dt\": "+ dt +
                "  \"totalAge\":" + mapperAge + " " +
                "}";
    }

    @RequestMapping("/getAgeByName")
    @ResponseBody
    public String getAgeByName(@RequestParam(value = "dt", defaultValue = "0") int dt){

        if (dt == 0) {
            dt = getToday();
        }
        Map ageByName = ageCntService.getAgeByName(dt);

        return "{" + "日期："+dt +"," +"info:" + ageByName.toString() +"}" ;
//        return "{" +
//                "\"dt\": "+ dt +
//                "\"姓名\" " + StringUtils.join(ageByName.keySet()) +
//                "\"年龄统计\"" + StringUtils.join(ageByName.values()) +
//                "}" ;

//        return "            {" +
//                "              \"status\": 0," +
//                "              \"姓名\": \"\"," +
//                "              \"dt\": "+ dt +
//                "                \"name\": [\"" +
//                StringUtils.join(ageByName.keySet(), "\",\"") +
//                "\"]," +
//                "                \"年龄统计\": [" +
//                "                  {" +
//                "                    \"data\": [" +
//                StringUtils.join(ageByName.values(), ",") +
//                "]" +
//                "                  }" +
//                "                ]" +
//                "              }" +
//                "            }";
    }

    @RequestMapping("/getMytest")
    @ResponseBody
    public String getMytest(@RequestParam(value = "dt", defaultValue = "0") int dt) {

        if (dt == 0) {
            dt = getToday();
        }
        StringBuilder jsonBuilder = new StringBuilder("{\"status\":0,\"data\":{\"mapData\":[");
        List<myBean> mytest = ageCntService.getMytest(dt);
//        if (mytest.size()==0) {
//        }
        for (int i = 0; i < mytest.size(); i++) {
            if (i >= 1) {
                jsonBuilder.append(",");
            }
            myBean beans = mytest.get(i);
            jsonBuilder.append("{\"id\":\""+"id\"" +"," + "\"name\":\"" + beans.getName() + "\",\"total_age\":" + beans.getTotal_age() + " }");

        }

        jsonBuilder.append("]}}");
        return jsonBuilder.toString();

    }


    private int getToday() {
        long ts = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(sdf.format(ts));
    }



}
