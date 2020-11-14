package com.yzdd.ec.dao;

import com.yzdd.ec.pojo.CartPojo;
import com.yzdd.ec.pojo.CommodityPojo;
import com.yzdd.ec.pojo.OrderPojo;
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
  public static String loginSelect(String name, String password){
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
  public static boolean addCommodity(CommodityPojo commodityPojo){
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

  //管理员删除商品方法
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
  public static boolean updateCom(int ID, String name, String price, String stock){
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

  //把商品从购物车移除的方法
      public static boolean deleteCart(int id){
          Connection connection=ToolsDao.getConnection();
          PreparedStatement preparedStatement=null;
          String sql="DELETE FROM shoppingcart WHERE id=?";
          try {
              preparedStatement = connection.prepareStatement(sql);
              preparedStatement.setInt(1,id);
              int a=preparedStatement.executeUpdate();
              if (a>0){
                  return true;
              }
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }
          return false;
      }

  //查询地址的方法
      public static String selectAddress(String user_root){
          String address=null;
          Connection connection=ToolsDao.getConnection();
          PreparedStatement preparedStatement=null;
          String sql="SELECT user_address FROM user WHERE user_root=?";
          ResultSet resultSet=null;
          try {
              preparedStatement=connection.prepareStatement(sql);
              preparedStatement.setString(1,user_root);
              resultSet=preparedStatement.executeQuery();
              if (resultSet.next()){
                  address = resultSet.getString("user_address");
              }
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }
          return address;
      }

 //修改地址的方法
      public static boolean updateAddress(String newAddress,String user_root){
          Connection connection=ToolsDao.getConnection();
          PreparedStatement preparedStatement=null;
          String sql="UPDATE user set user_address=? WHERE user_root=?";
          try {
              preparedStatement = connection.prepareStatement(sql);
              preparedStatement.setString(1,newAddress);
              preparedStatement.setString(2,user_root);
              int n=preparedStatement.executeUpdate();
              if (n>0){
                  return true;
              }
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }

          return false;
      }

 //把商品添加到订单表的方法
      public static boolean addOrder(String order_user, String order_comName, int order_num, String time, String order_address){
          Connection connection=ToolsDao.getConnection();
          PreparedStatement preparedStatement=null;
          String sql = "INSERT INTO `order` (order_user,order_datetime,order_commodity,order_num,order_address)VALUES(?,?,?,?,?);";
          try {
              preparedStatement = connection.prepareStatement(sql);
              preparedStatement.setString(1,order_user);
              preparedStatement.setString(2,time);
              preparedStatement.setString(3,order_comName);
              preparedStatement.setInt(4, order_num);
              preparedStatement.setString(5,order_address);
              int n=preparedStatement.executeUpdate();
              if (n > 0) {
                  return true;
              }
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }
          return false;
      }

  //这里是把商品加入购物车的方法
      public static boolean addCart(String user_id, String name, int num, int sumMoney){
          Connection connection=ToolsDao.getConnection();
          PreparedStatement preparedStatement=null;
          String sql="INSERT into shoppingcart (user_id,name,num,sumMoney)VALUES(?,?,?,?)";

          try {
              preparedStatement = connection.prepareStatement(sql);
              preparedStatement.setString(1,user_id);
              preparedStatement.setString(2,name);
              preparedStatement.setInt(3,num);
              preparedStatement.setInt(4,sumMoney);

              int n=preparedStatement.executeUpdate();
              if (n>0){
                  return true;
              }
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }
          return false;
      }

  //这里是展示购物车的查询方法
      public static List<CartPojo> selectCart(String user_id){
          Connection connection=ToolsDao.getConnection();
          PreparedStatement preparedStatement=null;
          ResultSet resultSet=null;

          List<CartPojo> list = new ArrayList<>();
          String sql = "SELECT * FROM shoppingcart WHERE user_id=?";

          try {
              preparedStatement = connection.prepareStatement(sql);
              preparedStatement.setString(1,user_id);

              resultSet=preparedStatement.executeQuery();
              while (resultSet.next()){
                  CartPojo cartPojo = new CartPojo();
                  cartPojo.setId(resultSet.getInt("id"));
                  cartPojo.setName(resultSet.getString("name"));
                  cartPojo.setNum(resultSet.getInt("num"));
                  cartPojo.setSumMoney(resultSet.getInt("sumMoney"));

                  list.add(cartPojo);
              }
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }
          return list;
      }

      //这是一个商品数据库的查询方法，把从数据库里查询到的商品信息存入一个list集合里面
      public static List<CommodityPojo> selectCommodity(){
          Connection connection=ToolsDao.getConnection();
          PreparedStatement preparedStatement=null;
          ResultSet resultSet=null;

          List<CommodityPojo> list=new ArrayList();
          String sql="SELECT * FROM commodity";
          try {
              preparedStatement=connection.prepareStatement(sql);
              resultSet=preparedStatement.executeQuery();
              while (resultSet.next()){
                  CommodityPojo commodity=new CommodityPojo();
                  commodity.setCommodity_name(resultSet.getString("commodity_name"));
                  commodity.setCommodity_Price(resultSet.getString("commodity_Price"));
                  commodity.setCommodity_stock(resultSet.getString("commodity_stock"));
                  list.add(commodity);
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
          return list;
      }

//  这是订单查询方法
    public static List<OrderPojo> selectOrder(String user_root){
      ResultSet resultSet=null;
      List<OrderPojo> orderPojoList=new ArrayList<>();
      Connection connection=ToolsDao.getConnection();
      PreparedStatement preparedStatement=null;
      String sql="SELECT * FROM `order` WHERE order_user=?";
      try {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,user_root);
        resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
          OrderPojo orderPojo = new OrderPojo();
          orderPojo.setOrder_id(resultSet.getString("order_ID"));
          orderPojo.setOrder_user(resultSet.getString("order_user"));
          orderPojo.setOrder_datetime(resultSet.getString("order_datetime"));
          orderPojo.setOrder_commodity(resultSet.getString("order_commodity"));
          orderPojo.setOrder_num(resultSet.getInt("order_num"));
          orderPojo.setOrder_address(resultSet.getString("order_address"));

          orderPojoList.add(orderPojo);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return orderPojoList;
    }

  //这是通过用户名 查询用户id的方法
      public static String selectUser_id(String root){
          String id=null;
          Connection connection=ToolsDao.getConnection();
          PreparedStatement preparedStatement=null;
          ResultSet resultSet=null;
          String sql="SELECT user_ID FROM user WHERE user_root=?";
          try {
              preparedStatement = connection.prepareStatement(sql);
              preparedStatement.setString(1,root);
              resultSet=preparedStatement.executeQuery();
              while (resultSet.next()){
                  id=resultSet.getString("user_id");
              }
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }

          return id;
      }

//      这是修改密码的方法
      public static boolean updatePassword(String user_root,String newPsw){
          Connection connection=ToolsDao.getConnection();
          String sql="UPDATE user set user_password=? WHERE user_root=?";
          PreparedStatement preparedStatement=null;
          try {
              preparedStatement = connection.prepareStatement(sql);
              preparedStatement.setString(1,newPsw);
              preparedStatement.setString(2,user_root);
              int num=preparedStatement.executeUpdate();
              if (num>0){
                  return true;
              }
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }
          return false;
      }
}

