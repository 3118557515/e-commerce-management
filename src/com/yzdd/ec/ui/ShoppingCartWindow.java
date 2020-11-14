package com.yzdd.ec.ui;

import com.yzdd.ec.dao.*;
import com.yzdd.ec.pojo.CartPojo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ShoppingCartWindow {
    private JPanel jpanel;
    private JLabel back;
    private JTable table1;
    private JButton 移除Button;
    private JButton 结算Button;
    private JLabel cartJLabel;
    private JFrame frame;
    private String user_id;
    private List<CartPojo> list;
    private DefaultTableModel model;
    private String user_root;

    public ShoppingCartWindow(String user_id, String user_root) {
        this.user_id = user_id;
        this.user_root = user_root;
        cartJLabel.setText(user_root + "的购物车");
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.setVisible(false);
                UserWindow userWindow = new UserWindow(user_id, user_root);
                userWindow.createView();
            }
        });
        移除Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int n = table1.getSelectedRow();
                if (n == -1) {
                    JOptionPane.showMessageDialog(null, "请选择一行数据再进行操作!");
                } else {
                    int id = (int) table1.getValueAt(n, 0);
//                    FunctionDao.DeleteCart deleteCart = new FunctionDao.DeleteCart();
                    if (FunctionDao.deleteCart(id)) {
                        JOptionPane.showMessageDialog(null, "删除成功");
                        table1.setModel(getModel());
                    } else {
                        JOptionPane.showMessageDialog(null, "操作失败");
                    }
                }
            }
        });
        结算Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = 0;
                for (int i = 0; i < table1.getRowCount(); i++) {
                    count += (int) table1.getValueAt(i, 3);
                }
                JOptionPane.showMessageDialog(null, "您一共需要支付:" + count + "元!");


                String order_user = user_root;


                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String order_datetime = dateFormat.format(date);


                String order_commodity = null;
                int order_num = 1;
                boolean flag = true;
                String order_address = FunctionDao.selectAddress(order_user);
                for (int j = 0; j < table1.getRowCount(); j++) {
                    order_commodity = (String) table1.getValueAt(j, 1);
                    order_num = (int) table1.getValueAt(j, 2);
                    boolean answer =FunctionDao.addOrder(order_user, order_commodity, order_num, order_datetime, order_address);
                    if (!answer) {
                        flag = false;
                    }
//                    FunctionDao.DeleteCart deleteCart = new FunctionDao.DeleteCart();
                    int id = (int) table1.getValueAt(j, 0);
                    if (FunctionDao.deleteCart(id) != true) {
                        flag = false;
                    }
                }
                if (flag == true) {
                    table1.setModel(getModel());
                    JOptionPane.showMessageDialog(null, "结算成功!");
                } else {
                    JOptionPane.showMessageDialog(null, "抱歉,结算失败");
                }
            }
        });
    }

    public void createView() {
        frame = new JFrame("ShoppingCartWindow");
        frame.setContentPane(jpanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public DefaultTableModel getModel() {
        list = FunctionDao.selectCart(user_id);
        String[] head = new String[]{"购物车编号", "商品", "数量", "单价"};
        Object[][] date = new Object[list.size()][head.length];
        for (int i = 0; i < list.size(); i++) {
            date[i][0] = list.get(i).getId();
            date[i][1] = list.get(i).getName();
            date[i][2] = list.get(i).getNum();
            date[i][3] = list.get(i).getSumMoney();
        }
        model = new DefaultTableModel(date, head);
        return model;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        table1 = new JTable(getModel());
    }
}
