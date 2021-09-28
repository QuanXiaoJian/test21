package com.powernode.web.servlet;

import com.powernode.service.ProductService;
import com.powernode.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteAll")
public class DeleteAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] ids = req.getParameterValues("id");

        ProductService productService=new ProductServiceImpl();
        boolean result=productService.delAll(ids);
        if(result){
            resp.sendRedirect("/index.html");
        }else {
            req.setAttribute("message","删除失败。");
            req.getRequestDispatcher("error/message.jsp").forward(req,resp);
        }


    }
}
