package com.yzdd.ec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePsw {
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
