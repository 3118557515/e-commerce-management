package com.yzdd.ec.dao;

import com.yzdd.ec.pojo.CommodityPojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ShowCommodity {
    //这是一个商品数据库的查询方法，把从数据库里查询到的商品信息存入一个list集合里面
    public List<CommodityPojo> selectCommodity(){
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
}
