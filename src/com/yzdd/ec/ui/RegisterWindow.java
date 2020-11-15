package com.yzdd.ec.ui;

import com.yzdd.ec.dao.FunctionDao;
import com.yzdd.ec.pojo.UserPojo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    private JTextField newAddress;
    private JLabel addressJLabel;
    private JPanel panel1;
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
        //收货地址输入框的键盘监听，点击回车开始注册验证
        newAddress.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int k = e.getKeyCode();
                if (k == KeyEvent.VK_ENTER) {
                    registrationVerification();
                }
            }
        });
        newUserJText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                userJLabel.setForeground(Color.red);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                userJLabel.setForeground(new Color(187, 187, 187));
            }
        });
        newPsw.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                pswJLabel.setForeground(Color.red);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                pswJLabel.setForeground(new Color(187, 187, 187));
            }
        });
        newPsw1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                pswJLabel1.setForeground(Color.red);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                pswJLabel1.setForeground(new Color(187, 187, 187));
            }
        });
        newEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                emailJLabel.setForeground(Color.red);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                emailJLabel.setForeground(new Color(187, 187, 187));
            }
        });
        newPhone.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                phoneJLabel.setForeground(Color.red);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                phoneJLabel.setForeground(new Color(187, 187, 187));
            }
        });
        newAddress.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                addressJLabel.setForeground(Color.red);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                addressJLabel.setForeground(new Color(187, 187, 187));
            }
        });
    }

    //创建注册窗口的方法
    public void CreateView() {
        frame = new JFrame("注册窗口");
        frame.setContentPane(jpanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(900, 600);
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
        userPojo.setUser_address(newAddress.getText());
        if (!(newUserJText.getText().equals("")) && !(newPsw.getText().equals(""))) {
            if (psw1.equals(userPojo.getUser_password())) {
                if (FunctionDao.insert(userPojo)) {
                    JOptionPane.showMessageDialog(null, "注册成功");
                    frame.setVisible(false);
                    LoginWindow loginWindow = new LoginWindow();
                    loginWindow.CreateView();
                } else {
                    JOptionPane.showMessageDialog(null, "用户名重复");
                }
            } else {
                JOptionPane.showMessageDialog(null, "两次输入密码不一致");
            }
        } else {
            JOptionPane.showMessageDialog(null, "对不起，账号和密码不能为空");
        }
    }
}
