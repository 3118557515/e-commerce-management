package com.yzdd.ec.ui;

import com.yzdd.ec.dao.DeleteCart;
import com.yzdd.ec.dao.ShowCart;
import com.yzdd.ec.dao.ShowUser_id;
import com.yzdd.ec.pojo.CartPojo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartWindow {
    private JPanel jpanel;
    private JLabel back;
    private JTable table1;
    private JButton 移除Button;
    private JButton 结算Button;
    private JLabel cartJLabel;
    private JFrame frame;
    private  String user_id;
    private List<CartPojo> list;
    private DefaultTableModel model;

    public ShoppingCartWindow(String user_id,String user_root) {
        this.user_id=user_id;
        cartJLabel.setText(user_root+"的购物车");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.setVisible(false);
                UserWindow userWindow=new UserWindow(user_id,user_root);
                userWindow.createView();
            }
        });
        移除Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int n = table1.getSelectedRow();
                if (n==-1){
                    JOptionPane.showMessageDialog(null,"请选择一行数据再进行操作!");
                }else {
                    int id = (int) table1.getValueAt(n, 0);
                    DeleteCart deleteCart=new DeleteCart();
                    if (deleteCart.deleteCart(id)){
                        JOptionPane.showMessageDialog(null,"删除成功");
                        table1.setModel(getModel());
                    }else {
                        JOptionPane.showMessageDialog(null,"操作失败");
                    }
                }
            }
        });
    }

    public  void createView(){
         frame = new JFrame("ShoppingCartWindow");
        frame.setContentPane(jpanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1200 ,800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public  DefaultTableModel getModel(){
        ShowCart showCart=new ShowCart();
        list = showCart.selectCart(user_id);
        String[] head= new String[]{"订单编号","商品", "数量", "单价"};
        Object[][] date=new Object[list.size()][head.length];
        for (int i = 0; i < list.size(); i++) {
            date[i][0]=list.get(i).getId();
            date[i][1]=list.get(i).getName();
            date[i][2]=list.get(i).getNum();
            date[i][3]=list.get(i).getSumMoney();
        }
        model = new DefaultTableModel(date, head);
        return model;
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        table1 = new JTable(getModel());
    }
}
