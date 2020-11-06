package com.yzdd.ec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//这是一个工具类，存放驱动加载、创建操作对象、关闭资源等方法
public class ToolsDao {
  static String DRIVER="com.mysql.jdbc.Driver";
  static String URL="jdbc:mysql://localhost:3306/ec?characterEncoding=UTF-8";
  static String USER="root";
  static String PASSWORD="admin123";
  static String PASSWORD1="000000";

  //加载驱动
  static {
    try {
      Class.forName(DRIVER);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  //创建连接对象
  public static Connection getConnection(){
    Connection connection=null;
    try {
      connection= DriverManager.getConnection(URL,USER,PASSWORD);
      return connection;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
