package com.yzdd.ec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//这是把商品从购物车移除的方法类
public class DeleteCart {
    public boolean deleteCart(int id){
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
}
