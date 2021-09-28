package com.powernode.dao.impl;

import com.powernode.dao.ProductDao;
import com.powernode.pojo.Product;
import com.powernode.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    public List getAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List list = new ArrayList();

        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement("select * from product");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setNum(rs.getString("num"));
                product.setDescription(rs.getString("description"));

                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeAll(conn, pstmt, rs);
        }
        return list;
    }

    @Override
    public int addPro(Product product) {
        Connection conn=null;
        PreparedStatement ps=null;
        int i=-1;
        try{
            conn=JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            String sql="insert into product (name,price,num,description) values(?,?,?,?)";
             ps = conn.prepareStatement(sql);
           //ps.setString(1,product.getId());
           ps.setString(1,product.getName());
           ps.setString(2,product.getPrice());
           ps.setString(3,product.getNum());
           ps.setString(4,product.getDescription());

             i = ps.executeUpdate();

            conn.commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn!=null) {
                try {
                    conn.rollback();
                }catch (SQLException e1){

                }
            }
            JDBCUtils.closeAll(conn,ps,null);
        }



        return i;
    }
    public int deletePro(String id){
        Connection conn=null;
        PreparedStatement ps=null;
        int i=-1;
        try{
            conn=JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            String sql="delete from product where id=?";
            ps = conn.prepareStatement(sql);
            //ps.setString(1,product.getId());

            ps.setString(1,id);


            i = ps.executeUpdate();

            conn.commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn!=null) {
                try {
                    conn.rollback();
                }catch (SQLException e1){

                }
            }
            JDBCUtils.closeAll(conn,ps,null);
        }




        return i;
    }

    public Product getEdit(String id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Product product = new Product();
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement("select id,name,price,num,description from product where id=?");
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //Product product = new Product();
                product.setId(rs.getString("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getString("price"));
                product.setNum(rs.getString("num"));
                product.setDescription(rs.getString("description"));

                //list.add(product);
            }
        } catch (Exception e) {


            e.printStackTrace();
        } finally {
            JDBCUtils.closeAll(conn, pstmt, rs);
        }
        return product;
    }
    public int setPro(Product pro){
        //Product pro = getEdit(id);

        Connection conn=null;
        PreparedStatement ps=null;
        int i=-1;
        try{
            conn=JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            String sql="update product set name=?,price=?,num=?,description=? where id=?";
            ps = conn.prepareStatement(sql);
            //ps.setString(1,product.getId());
            ps.setString(1,pro.getName());
            ps.setString(2,pro.getPrice());
            ps.setString(3,pro.getNum());
            ps.setString(4,pro.getDescription());

            ps.setString(5,pro.getId());


            i = ps.executeUpdate();

            conn.commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn!=null) {
                try {
                    conn.rollback();
                }catch (SQLException e1){

                }
            }
            JDBCUtils.closeAll(conn,ps,null);
        }




        return i;
    }
    public int deleteMore(){
        int i=-1;
        return i;
    }
}
