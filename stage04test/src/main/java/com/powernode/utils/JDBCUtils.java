package com.powernode.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 将JDBC程序中的一些固定代码提取出来，方便使用
 * 1.注册驱动(程序中只需要执行一次)
 * 2.建立连接
 * 6.关闭资源
 */
public class JDBCUtils {
    private static  String driver;
    private static  String url;
    private static  String username;
    private static  String password;

    static {
        try {
            //可以直接赋值
//            driver = "com.mysql.jdbc.Driver";
//            url = "jdbc:mysql://localhost:3306/bjpowernode";
//            username = "root";
//            password = "1234";
            //将数据放在不需要编译，可以直接运行的文件中(xml,properties...)
            Properties properties = new Properties();
//            properties.load(new FileInputStream("jdbc/src/db.properties"));
            properties.load(JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties"));

            driver = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            username = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 返回一个链接数据库的对象
     */
    public static Connection getConnection(){
        try {
           return DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 关闭程序中使用过的对象
     */
    public  static  void  closeAll(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对于DML语句可以将JDBC的六个步骤全部的封装在工具类中，使用时直接调用即可
     * @param sql       要处理的DML语句
     * @param args    sql语句中的占位符
     */
    public  static int myExecuteUpdate(String sql,Object...args){
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {

             preparedStatement = connection.prepareStatement(sql);
             //是否存在占位符
            if(args != null){
                for (int i = 0;i<args.length;i++){
                    preparedStatement.setObject(i+1,args[i]);
                }
            }
            //执行SQL语句
            int i = preparedStatement.executeUpdate();
            return  i;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeAll(connection,preparedStatement,null);
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
