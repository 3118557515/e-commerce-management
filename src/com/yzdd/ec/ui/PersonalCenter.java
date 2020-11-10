package com.yzdd.ec.ui;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PersonalCenter {
    private JPanel maxJPanel;
    private JLabel userLabel;
    private JLabel pswLabel;
    private JLabel addressLabel;
    private JLabel back;
    private JLabel perJLabel;
  private JLabel address;
  private  JFrame frame;
    private  String user_id;

    public PersonalCenter(String user_id,String user_root) {
        this.user_id=user_id;
        this.userLabel.setText(user_root);
        this.pswLabel.setText("**********");
        perJLabel.setText(user_root+"的个人中心");
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

    public  void createView(){
       frame = new JFrame("PersonalCenter");
        frame.setContentPane(maxJPanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1200 ,800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public static void main(String[] args) {

    }
}
