package com.yzdd.ec.ui;

import com.yzdd.ec.dao.FunctionDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePswUi extends JDialog{
    private JPanel jpanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton updateBtn;
    private JLabel user_rootJLabel;
    private JFrame frame;

    public UpdatePswUi(String user_root) {
        user_rootJLabel.setText(user_root);
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String answer=FunctionDao.select(user_root,textField1.getText());
                if (!answer.equals("no")){
                    if (textField2.getText().equals(textField3.getText())){
                        boolean b=com.yzdd.ec.dao.UpdatePsw.updatePassword(user_root,textField3.getText());
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
        });
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
