package com.yzdd.ec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Address {
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
}
