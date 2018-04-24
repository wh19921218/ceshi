package com.cn.lianshou.common.util;

import java.sql.*;

/**
 * FileName: com.cn.lianshou.common.util.DataUtil.java
 * Author: Wanghh
 * Date: 2018/3/13 17:12
 */
public class DataUtil {

        public  Connection getConnection()
        {
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            }catch(ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            String user="root";
            String password="root";
            String url="jdbc:mysql://192.168.1.31:3306;DatabaseName=qiansudai";
            Connection con=null;
            try{
                con=DriverManager.getConnection(url,user,password);
            }catch(SQLException e)
            {
                e.printStackTrace();
            }
            return con;
        }

        public  String selectPassword(String username)
        {
            Connection connection=getConnection();
            String sql="select *from login where username=?";
            PreparedStatement preparedStatement=null;
            ResultSet result=null;
            String password=null;
            try{
                preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,username);

                result=preparedStatement.executeQuery();//可执行的     查询
                if(result.next())
                    password=result.getString("password");

            }catch(SQLException e){
                e.printStackTrace();
            }finally
            {
                close(preparedStatement);
                close(result);
                close(connection);
            }
            System.out.println("找到的数据库密码为："+password);
            return password;
        }
        public  void close (Connection con)
        {
            try{
                if(con!=null)
                {
                    con.close();
                }
            }catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        public  void close (PreparedStatement preparedStatement)
        {
            try{
                if(preparedStatement!=null)
                {
                    preparedStatement.close();
                }
            }catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        public  void close(ResultSet resultSet)
        {
            try{
                if(resultSet!=null)
                {
                    resultSet.close();
                }
            }catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
}
