package com.yzdd.ec.ui;

import com.yzdd.ec.dao.FunctionDao;
import com.yzdd.ec.pojo.UserPojo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterWindow {
    private JPanel jpanel1;
    private JTextField newUserJText;
    private JPasswordField newPsw;
    private JTextField newEmail;
    private JTextField newPhone;
    private JLabel userJLabel;
    private JLabel pswJLabel;
    private JLabel emailJLabel;
    private JLabel phoneJLabel;
    private JButton btnGoRegister;
    private JButton btnGoLogin;
    private JPasswordField newPsw1;
    private JLabel pswJLabel1;
    static JFrame frame;

    public RegisterWindow() {
        //去登录按钮的点击事件，点击后跳转到登录窗口
        btnGoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.CreateView();
            }
        });
        //开始注册按钮的点击事件，点击后开始注册验证
        btnGoRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrationVerification();
            }
        });
        //手机号码输入框的键盘监听，点击空格开始注册验证
        newPhone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int k = e.getKeyCode();
                if (k == KeyEvent.VK_ENTER) {
                    registrationVerification();
                }
            }
        });
    }

    //创建注册窗口的方法
    public void CreateView() {
        frame = new JFrame("注册窗口");
        frame.setContentPane(jpanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1200 ,800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //注册验证的方法
    public void registrationVerification() {
        UserPojo userPojo = new UserPojo();
        userPojo.setUser_root(newUserJText.getText());
        userPojo.setUser_password(newPsw.getText());
        String psw1 = newPsw1.getText();
        userPojo.setUser_TelephoneNumber(newPhone.getText());
        userPojo.setUser_email(newEmail.getText());
        if (psw1.equals(userPojo.getUser_password())) {
            if (FunctionDao.insert(userPojo)) {
                JOptionPane.showMessageDialog(null, "注册成功");
            } else {
                JOptionPane.showMessageDialog(null, "用户名重复");
            }
        } else {
            JOptionPane.showMessageDialog(null, "两次输入密码不一致");
        }
    }
}
