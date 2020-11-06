package com.yzdd.ec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//这里是通过用户id查询用户名字的方法类
public class ShowUserName {
    public static String selectUserName(String user_id){
        Connection connection=ToolsDao.getConnection();
        PreparedStatement preparedStatement=null;
        String sql="SELECT user_root FROM user WHERE user_ID=?";
        ResultSet resultSet=null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user_id);
            resultSet=preparedStatement.executeQuery();

            if (resultSet.next()){
                return String.valueOf(resultSet.getInt("user_root"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
