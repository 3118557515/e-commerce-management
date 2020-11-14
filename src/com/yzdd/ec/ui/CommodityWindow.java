package com.yzdd.ec.ui;

import com.yzdd.ec.dao.FunctionDao;
import com.yzdd.ec.pojo.CommodityPojo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommodityWindow {
  private JPanel panel1;
  private JTextField commodity_nametextField;
  private JTextField commodity_PricetextField;
  private JTextField commodity_stocktextField;
  private JButton determineButton;
  private JLabel commodity_name;
  private JLabel commodity_Price;
  private JLabel commodity_stock;

  //商品增加
  public CommodityWindow() {
    determineButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        CommodityPojo commodityPojo= new CommodityPojo();
        commodityPojo.setCommodity_name(commodity_nametextField.getText());
        commodityPojo.setCommodity_Price(commodity_PricetextField.getText());
        commodityPojo.setCommodity_stock(commodity_stocktextField.getText());
        FunctionDao functionDao = new FunctionDao();
        functionDao.addCommodity(commodityPojo);

      }
    });
  }

  public void createView(){
    JFrame frame = new JFrame("CommodityWindow");
    frame.setContentPane(new CommodityWindow().panel1);
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);


  }

}
