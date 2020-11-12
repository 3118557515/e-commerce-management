package com.yzdd.ec.dao;

import com.yzdd.ec.pojo.OrderPojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowOrder {
  public  List<OrderPojo> selectOrder(String user_root){
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
}
