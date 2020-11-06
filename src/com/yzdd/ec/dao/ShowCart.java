package com.yzdd.ec.dao;

import com.yzdd.ec.pojo.CartPojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//这里是展示购物车的查询方法类
public class ShowCart {
    public List<CartPojo> selectCart(String user_id){
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
}
