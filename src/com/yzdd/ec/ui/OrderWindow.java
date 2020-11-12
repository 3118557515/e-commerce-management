package com.yzdd.ec.ui;

import com.yzdd.ec.dao.ShowOrder;
import com.yzdd.ec.pojo.OrderPojo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class OrderWindow {
    public JPanel jpanel;
    private JLabel back;
    private JLabel orderJLabel;
    private JTable table1;
    private JFrame frame;
    private  String user_id;
    private  String user_root;
    private List<OrderPojo> list;

    public OrderWindow(String user_id,String user_root) {
        this.user_id=user_id;
        this.user_root=user_root;
        orderJLabel.setText(user_root+"的订单");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.setVisible(false);
                UserWindow userWindow=new UserWindow(user_id,user_root);
                userWindow.createView();
            }
        });
    }

    public   void  createView(){
        frame = new JFrame("orderWindow");
        frame.setContentPane(jpanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1200 ,800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public DefaultTableModel getModel(){
      DefaultTableModel model=null;
      String[] head={"订单编号","下单商品","下单数量","送货地址","下单时间"};
      ShowOrder showOrder=new ShowOrder();

       list = showOrder.selectOrder(user_root);
      Object[][] date = new Object[list.size()][head.length];
      for (int i = 0; i < list.size(); i++) {
        date[i][0]=list.get(i).getOrder_id();
        date[i][1]=list.get(i).getOrder_commodity();
        date[i][2]=list.get(i).getOrder_num();
        date[i][3]=list.get(i).getOrder_address();
        date[i][4]=list.get(i).getOrder_datetime();
      }
      model = new DefaultTableModel(date, head);
      return  model;
    }
  private void createUIComponents() {
    // TODO: place custom component creation code here
    table1 = new JTable(getModel());
  }
}
