package com.yzdd.ec.ui;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderWindow {
    public JPanel jpanel;
    private JLabel back;
    private JLabel orderJLabel;
    private JTable table1;
    private JFrame frame;
    private  String user_id;

    public OrderWindow(String user_id,String user_root) {
        this.user_id=user_id;
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
    public static void main(String[] args) {

    }
}
