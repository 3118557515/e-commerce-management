package com.yzdd.ec.ui;

import com.yzdd.ec.dao.FunctionDao;
import com.yzdd.ec.pojo.CommodityPojo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCommWindow {
  private JPanel panel1;
  private JButton btnRegister;
  private JButton determineadd;
  private JLabel userJLabel;
  private JLabel pswJLabel;
  private JTextField textField1;
  private JTextField textField2;
  private JTextField textField3;
  private JTextField textField4;
  private CommodityPojo commodityPojo;
  private JFrame frame;

  public UpdateCommWindow(CommodityPojo commodityPojo) {
    determineadd.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int ID= Integer.parseInt(textField1.getText());
        String name = textField2.getText();
        String price = textField3.getText();
        String stock = textField4.getText();
        if (FunctionDao.update(ID,name,price,stock)){
          JOptionPane.showMessageDialog(null,"修改成功");
        }else {
          JOptionPane.showMessageDialog(null,"修改失败");
        }
      }
    });
    this.commodityPojo = commodityPojo;
    textField1.setText(String.valueOf(commodityPojo.getCommodity_ID()));
    textField2.setText(commodityPojo.getCommodity_name());
    textField3.setText(commodityPojo.getCommodity_Price());
    textField4.setText(commodityPojo.getCommodity_stock());

      btnRegister.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              frame.setVisible(false);
          }
      });
  }

  public void createView(){
    frame = new JFrame("UpdateCommWindow");
    frame.setContentPane(panel1);
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

}
