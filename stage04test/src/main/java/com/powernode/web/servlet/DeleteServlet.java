package com.powernode.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.powernode.dao.ProductDao;
import com.powernode.dao.impl.ProductDaoImpl;
import com.powernode.service.ProductService;
import com.powernode.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/deletepro")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        ProductService productDao=new ProductServiceImpl();
        productDao.deletePro(id);
        Map map=new HashMap();
        map.put("success","true");
        map.put("msg","成功");

        ObjectMapper objectMapper=new ObjectMapper();
        String s = objectMapper.writeValueAsString(map);
        resp.getWriter().print(s);

        /*if (i==1){

            resp.sendRedirect("/index.html");
        }else {
            req.setAttribute("message","删除失败!");
            req.getRequestDispatcher("error/message.jsp").forward(req,resp);
        }*/
    }
}
