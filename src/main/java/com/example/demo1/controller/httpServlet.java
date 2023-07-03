package com.example.demo1.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author :
 * @description :
 * @DateTime : 2023-02-14 23:40
 * @Version 1.0
 */
@WebServlet(urlPatterns = {"/add"})
public class httpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");
        String season = request.getParameter("season");
        String team = request.getParameter("team");
        String interesting = request.getParameter("interesting");

        System.out.println(user_name);

    }
}
