package com.yzdd.ec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//这里是把商品加入购物车的方法类
public class AddCart {
    public boolean AddCart(String user_id,String name,int num,int sumMoney){
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
}
