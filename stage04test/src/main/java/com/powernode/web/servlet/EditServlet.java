package com.powernode.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.powernode.dao.ProductDao;
import com.powernode.dao.impl.ProductDaoImpl;
import com.powernode.pojo.Product;
import com.powernode.service.ProductService;
import com.powernode.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        ProductService productDao=new ProductServiceImpl();
        Product pro = productDao.getEdit(id);

        req.setAttribute("editpro",pro);
        req.getRequestDispatcher("/edit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String num = req.getParameter("num");
        String description = req.getParameter("description");
        Product product=new Product(id,name,price,num,description);

        ProductService productDao=new ProductServiceImpl();
     productDao.setPro(product);

        resp.sendRedirect("index_ajax.jsp");
       /* Map resultMap = new HashMap();
        resultMap.put("success", true);
        resultMap.put("msg", "操作成功！");
        ObjectMapper objectMapper=new ObjectMapper();
        String json = objectMapper.writeValueAsString(resultMap);

        resp.getWriter().print(json);
        resp.getWriter().close();*/
        /*if(i==1){

            resp.sendRedirect("/index.html");
        }else {
            req.setAttribute("message","修改失败，请重新修改。");
            req.getRequestDispatcher("error/message.jsp").forward(req,resp);
        }*/

    }
}
