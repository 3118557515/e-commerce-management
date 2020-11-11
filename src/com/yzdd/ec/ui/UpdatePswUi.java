package com.yzdd.ec.ui;

import com.yzdd.ec.dao.FunctionDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UpdatePswUi extends JDialog{
    private JPanel jpanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton updateBtn;
    private JLabel user_rootJLabel;
    private JFrame frame;
    private String user_root1;

    public UpdatePswUi(String user_root) {
        user_root1=user_root;
        user_rootJLabel.setText(user_root);

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testUpdate();
            }
        });
        textField3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int k=e.getKeyCode();
                if (k == KeyEvent.VK_ENTER) {
                    testUpdate();
                }
            }
        });
    }
    public  void testUpdate(){
        String answer=FunctionDao.select(user_root1,textField1.getText());
        if (!answer.equals("no")){
            if (textField2.getText().equals(textField3.getText())){
                boolean b=com.yzdd.ec.dao.UpdatePsw.updatePassword(user_root1,textField3.getText());
                if (b){
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(null,"修改密码成功!");
                }else {
                    JOptionPane.showMessageDialog(null,"抱歉,修改密码失败!");
                }
            }else {
                JOptionPane.showMessageDialog(null,"两次新密码不一致");
            }
        }else {
            JOptionPane.showMessageDialog(null,"旧密码错误");
        }
    }

    public void createView() {
        frame = new JFrame("UpdatePsw");
        frame.setContentPane(jpanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(300,200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
