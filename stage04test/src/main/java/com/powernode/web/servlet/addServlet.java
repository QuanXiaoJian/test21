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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/addpro")
public class addServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");

        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String num = req.getParameter("num");
        String description = req.getParameter("description");
        Product product=new Product(name,price,num,description);


        ProductService productDao=new ProductServiceImpl();
        productDao.addPro(product);

        Map resultMap = new HashMap();
        resultMap.put("success", true);
        resultMap.put("msg", "操作成功！");
        ObjectMapper objectMapper=new ObjectMapper();
        String json = objectMapper.writeValueAsString(resultMap);

        resp.getWriter().print(json);
        resp.getWriter().close();

        /*PrintWriter pw = resp.getWriter();
        if(i==1){

           resp.sendRedirect("/index.html");
        //req.getRequestDispatcher("index.html").forward(req,resp);
        }else {

            req.setAttribute("message","添加失败，请重新添加。");
            req.getRequestDispatcher("error/message.jsp").forward(req,resp);
        }
*/

    }
}
