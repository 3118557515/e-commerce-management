package com.yzdd.ec.dao;

import com.yzdd.ec.pojo.CommodityPojo;
import com.yzdd.ec.pojo.UserPojo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//这是一个功能类，里面存放的是增删改查的方法
public class FunctionDao {
  //登录方法
  public static String select(String name,String password){
    ResultSet resultSet=null;
    PreparedStatement preparedStatement=null;
    Connection connection = ToolsDao.getConnection();
    String sql="select user_password from user where user_root=?";
    String selectAdmin="select admin_password from admin where admin_root=?";


    try {
      //用户输入账户密码后，先判断是不是管理员用户
      preparedStatement=connection.prepareStatement(selectAdmin);
      preparedStatement.setString(1,name);
      resultSet =preparedStatement.executeQuery();
      //如果是管理员用户，则返回admin
      if (resultSet.next()){
        String psw=resultSet.getString("admin_password");
        if (psw.equals(password)){
          String admin="admin";
          return admin;
        }
        //如果不是管理员用户，则判断是不是普通用户
      }else {
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,name);
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
          String psw=resultSet.getString("user_password");
          if (psw.equals(password)){
            String user="user";
            return user;
          }
        }
      }
      //如果既不是管理员用户，也不是普通用户则在最后返回空值
    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      try {
        resultSet.close();
        preparedStatement.close();
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    String no="no";
    return no;
  }

  //注册方法
  public static boolean insert(UserPojo userPojo){
    //增加语句
    String sql="INSERT into user(user_root,user_password,user_TelephoneNumber,user_email,user_address)VALUES(?,?,?,?,?)";
    //查询用户名是否已经存在的语句
    String sql1="SELECT user_root FROM user WHERE user_root=?";
    Connection connection=ToolsDao.getConnection();
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
    try {
      preparedStatement=connection.prepareStatement(sql1);
      preparedStatement.setString(1,userPojo.getUser_root());
      resultSet =preparedStatement.executeQuery();
      //如果有查询有返回值，则代表用户名在数据库中已经存在了，重复
      if (resultSet.next()){
        return false;
      }else {
        //如果没有返回值，则执行添加语句
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,userPojo.getUser_root());
        preparedStatement.setString(2,userPojo.getUser_password());
        preparedStatement.setString(3,userPojo.getUser_TelephoneNumber());
        preparedStatement.setString(4,userPojo.getUser_email());
        preparedStatement.setString(5,userPojo.getUser_address());
        preparedStatement.executeUpdate();
        return  true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }finally {
      try {
        resultSet.close();
        preparedStatement.close();
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return false;
  }
  //管理员查询商品方法
  public static List<CommodityPojo> selectView(){
    List<CommodityPojo> commoditys = new ArrayList<>();
    try {
      Connection connection = ToolsDao.getConnection();
      String sql = "select * from commodity ";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        CommodityPojo commodityPojo = new CommodityPojo();
        commodityPojo.setCommodity_ID(resultSet.getInt("commodity_ID"));
        commodityPojo.setCommodity_name(resultSet.getString("commodity_name"));
        commodityPojo.setCommodity_Price(resultSet.getString("commodity_Price"));
        commodityPojo.setCommodity_stock(resultSet.getString("commodity_stock"));
        commoditys.add(commodityPojo);
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return commoditys;
  }

  //管理员添加商品方法
  public static boolean increasecommodity(CommodityPojo commodityPojo){
    Connection connection = ToolsDao.getConnection();
    String sql = "insert into commodity(commodity_name,commodity_Price,commodity_stock) value(?,?,?) ";
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1,commodityPojo.getCommodity_name());
      preparedStatement.setString(2,commodityPojo.getCommodity_Price());
      preparedStatement.setString(3,commodityPojo.getCommodity_stock());
      int n = preparedStatement.executeUpdate();
      if (n>0){
        JOptionPane.showMessageDialog(null,"添加成功");
      }else{
        JOptionPane.showMessageDialog(null,"添加失败");
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return false;
  }
  //管理员删除方法
  public static   boolean deleteCom(int id){
    Connection connection = ToolsDao.getConnection();
    String sql = "delete from commodity where commodity_ID = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1,id);
      int n = preparedStatement.executeUpdate();
      if (n>0){
        return true;
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return false;

  }
  //管理员搜索方法
  public static List<CommodityPojo> query(String commodityName){
    List<CommodityPojo> commodityPojos = new ArrayList<>();
    try {
      Connection connection = ToolsDao.getConnection();
      String sql = "select * from commodity where commodity_name = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1,commodityName);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()){
        CommodityPojo commodityPojo = new CommodityPojo();
        commodityPojo.setCommodity_ID(resultSet.getInt("commodity_ID"));
        commodityPojo.setCommodity_name(resultSet.getString("commodity_name"));
        commodityPojo.setCommodity_Price(resultSet.getString("commodity_Price"));
        commodityPojo.setCommodity_stock(resultSet.getString("commodity_stock"));
        commodityPojos.add(commodityPojo);
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return commodityPojos;
  }
  //管理员修改商品方法
  public static boolean update(int ID,String name,String price,String stock){
    Connection connection = ToolsDao.getConnection();
    try {
      String sql = "update commodity set commodity_name = ?, commodity_Price = ?,commodity_stock = ? where commodity_ID = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1,name);
      preparedStatement.setString(2,price);
      preparedStatement.setString(3,stock);
      preparedStatement.setInt(4,ID);
      int n = preparedStatement.executeUpdate();
      if (n>0){
        return true;
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return false;
  }

}

