package com.yzdd.ec.ui;
//狂奔的蜗牛已被占用

import com.yzdd.ec.dao.FunctionDao;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginWindow extends  JFrame{
    private JPanel jpanel1;
    private JButton btnLogin;

    private JTextField userJText;
    private JPasswordField pswJPassword;
    private JButton btnRegister;
    private JLabel userJLabel;
    private JLabel pswJLabel;
    static JFrame frame;



    public LoginWindow() {
        //注册按钮的点击事件，点击跳转到登录界面。
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                RegisterWindow registerWindow = new RegisterWindow();
                registerWindow.CreateView();
            }
        });

        //登录按钮的点击事件，点击调用登录验证
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateLogon();
            }
        });

        //密码框里的键盘监听，按回车调用登录验证
        pswJPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int k = e.getKeyCode();
                if (k == KeyEvent.VK_ENTER) {
//                    System.out.println("键盘");
                    validateLogon();
                }
            }
        });
    }

    //程序入口
    public static void main(String[] args) {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.CreateView();
    }

    //创建登录窗口的方法
    public void CreateView() {
        frame = new JFrame("登录注册窗口");
        frame.setContentPane(jpanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //进行登录验证的方法
    public void validateLogon() {
        String root = userJText.getText();
        String psw = pswJPassword.getText();
        String answer = FunctionDao.loginSelect(root, psw);
        //equals方法前面不能是空值null，所以select方法的返回值answer必须是有值的
        if (answer.equals("admin")) {
            JOptionPane.showMessageDialog(null, "管理员用户登录成功");
            frame.setVisible(false);
            AdminWindow adminWindow=new AdminWindow();
            adminWindow.createView();
        } else if (answer.equals("user")) {
//            JOptionPane.showMessageDialog(null, "普通用户登录成功");
            frame.setVisible(false);
            UserWindow userWindow = new UserWindow(FunctionDao.selectUser_id(root),root);
            userWindow.createView();
        } else if (answer.equals("no")) {
            JOptionPane.showMessageDialog(null, "用户名或密码错误");
        }
    }
}
