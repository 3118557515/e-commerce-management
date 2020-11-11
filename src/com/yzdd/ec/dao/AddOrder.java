package com.yzdd.ec.dao;

import java.sql.*;

import static java.sql.Time.valueOf;

public  class AddOrder {
    public boolean addOrder(String order_user, String order_comName, int order_num, String time, String order_address){
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
}
