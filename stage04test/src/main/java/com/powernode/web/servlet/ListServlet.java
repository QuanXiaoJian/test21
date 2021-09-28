package com.powernode.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.powernode.service.ProductService;
import com.powernode.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index.ajax")
public class ListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ProductService productService = new ProductServiceImpl();
        List pList = productService.getAll();

        ObjectMapper objectMapper=new ObjectMapper();
        String s = objectMapper.writeValueAsString(pList);

        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().print(s);
        resp.getWriter().close();
        /*// 将数据存入request作用域
        req.setAttribute("pList", pList);
        req.getRequestDispatcher("/index_ajax.jsp").forward(req, resp);*/
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
