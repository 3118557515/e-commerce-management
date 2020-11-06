package com.yzdd.ec.ui;

import com.yzdd.ec.dao.FunctionDao;
import com.yzdd.ec.pojo.CommodityPojo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminWindow {
  private JPanel panel1;
  private JTable table1;
  private JButton increaseButton;
  private JButton deleteButton;
  private JButton RefreshButton;
  private JButton 修改Button;
  private DefaultTableModel tableModel;
  private List<CommodityPojo> list;


  public AdminWindow() {
    //点击添加跳转
    increaseButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        CommodityWindow commodityWindow = new CommodityWindow();
        commodityWindow.createView();
      }
    });
    //点击刷新事件
    RefreshButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      upadteTabel();
    }
  });
    //点击删除事件
    deleteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int n = table1.getSelectedRow();
        if(table1.getSelectedRow()==-1){
          JOptionPane.showMessageDialog(null,"请选择行");
        }else {
          CommodityPojo commodityPojo = list.get(n);
          if(FunctionDao.deleteCom(commodityPojo.getCommodity_ID())){
            JOptionPane.showMessageDialog(null,"删除成功");
          }else {
            JOptionPane.showMessageDialog(null,"删除失败");
          }
        }
      }
    });
  }

  public void createView() {
    JFrame frame = new JFrame("AdminWindow");
    frame.setContentPane(new AdminWindow().panel1);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
  }

    private void createUIComponents() {
     table1 = new JTable(getTableModel());
  }

  public DefaultTableModel getTableModel() {
    String [] hds = {"商品id","商品名","商品价格","商品库存"};
    FunctionDao functionDao = new FunctionDao();
    list = functionDao.selectView();
    Object[][] objects = new Object[list.size()][hds.length];
    for (int i=0; i<list.size(); i++){
      CommodityPojo commodityPojo = list.get(i);
      objects[i][0]= commodityPojo.getCommodity_ID();
      objects[i][1]= commodityPojo.getCommodity_name();
      objects[i][2]= commodityPojo.getCommodity_Price();
      objects[i][3]= commodityPojo.getCommodity_stock();
    }
    tableModel = new DefaultTableModel(objects, hds);
    return tableModel;
  }
  //刷新的方法
  public void upadteTabel() {
    table1.setModel(getTableModel());
  }

}
