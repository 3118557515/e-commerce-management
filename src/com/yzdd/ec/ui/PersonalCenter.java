package com.yzdd.ec.ui;

import com.yzdd.ec.dao.FunctionDao;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

import javax.swing.*;
import java.awt.event.*;

public class PersonalCenter {
    private JPanel maxJPanel;
    private JLabel userLabel;
    private JLabel pswLabel;
    private JLabel addressLabel;
    private JLabel back;
    private JLabel perJLabel;
  private JLabel address;
    private JButton copyRoot;
    private JButton copyAddress;
    private JButton updatePswJLabel;
    private JButton updateAddress;
    private  JFrame frame;
    private  String user_id;

    public PersonalCenter(String user_id,String user_root) {
        this.user_id=user_id;
        this.userLabel.setText(user_root);
        this.address.setText(FunctionDao.selectAddress(user_root));
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
        copyRoot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String myString =userLabel.getText();
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                JOptionPane.showMessageDialog(null,"复制成功!");
            }
        });
        copyAddress.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String myString =address.getText();
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                JOptionPane.showMessageDialog(null,"复制成功!");
            }
        });
        updatePswJLabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdatePswUi updatePswUi=new UpdatePswUi(user_root);
                updatePswUi.createView();
            }
        });
        updateAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newAddress=JOptionPane.showInputDialog(null,"请输入新地址:");
                if (newAddress!=null){
                    if (FunctionDao.updateAddress(newAddress,user_root)){
                        JOptionPane.showMessageDialog(null,"修改地址成功!");
                        address.setText(newAddress);
                    }else {
                        JOptionPane.showMessageDialog(null,"修改地址失败!");
                    }
                }
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
}
