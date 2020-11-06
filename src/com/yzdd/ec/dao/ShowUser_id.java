package com.yzdd.ec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//这是通过用户名 查询用户id的方法类
public class ShowUser_id {
    public String selectUser_id(String root){
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
}
