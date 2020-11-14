package com.yzdd.ec.ui;

import com.yzdd.ec.dao.FunctionDao;
import com.yzdd.ec.pojo.CommodityPojo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminWindow {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTable table1;
    private JButton DeleteButton;
    private JButton ModifyButton;
    private JTextField textField1;
    private JButton SelectButton;
    private JTextField commodity_nametextField;
    private JTextField commodity_PricetextField;
    private JTextField commodity_stocktextField;
    private JButton determineButton;
    private JButton RefreshButton;
    private DefaultTableModel tableModel;
    private List<CommodityPojo> list;
    private List<CommodityPojo> queryList;

    //点击删除按钮
    public AdminWindow() {
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = table1.getSelectedRow();
                if(table1.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(null,"请选择行");
                }else {
                    CommodityPojo commodityPojo = list.get(n);
                    if (FunctionDao.deleteCom(commodityPojo.getCommodity_ID())) {
                        JOptionPane.showMessageDialog(null, "删除成功");
                        updateTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "删除失败");
                    }
                }
            }
        });
        //点击增加按钮
        determineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommodityPojo commodityPojo= new CommodityPojo();
                commodityPojo.setCommodity_name(commodity_nametextField.getText());
                commodityPojo.setCommodity_Price(commodity_PricetextField.getText());
                commodityPojo.setCommodity_stock(commodity_stocktextField.getText());
                FunctionDao functionDao = new FunctionDao();
                functionDao.addCommodity(commodityPojo);
                updateTable();
            }
        });

    //点击修改按钮
      ModifyButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int position = table1.getSelectedRow();
          if (position==-1) {
            JOptionPane.showMessageDialog(null, "请选择行");
          }else {
            CommodityPojo commodityPojo = list.get(position);
            UpdateCommWindow updateCommWindow = new UpdateCommWindow(commodityPojo);
            updateCommWindow.createView();
          }
        }
      });
      //点击刷新的方法
      RefreshButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          updateTable();
        }
      });
      //点击查询按钮
        SelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.setModel(queryTableModel());
            }
        });
    }
    //查询商品展示
    public DefaultTableModel queryTableModel(){
        String table = textField1.getText();
        queryList = FunctionDao.query(table);
        String [] hds = {"商品id","商品名","商品价格","商品库存"};
        Object[][] objects = new Object[queryList.size()][hds.length];
        for (int i = 0; i <queryList.size() ; i++) {
            CommodityPojo commodityPojo = queryList.get(i);
            objects[i][0]= commodityPojo.getCommodity_ID();
            objects[i][1]= commodityPojo.getCommodity_name();
            objects[i][2]= commodityPojo.getCommodity_Price();
            objects[i][3]= commodityPojo.getCommodity_stock();
        }
        DefaultTableModel queryModel = new DefaultTableModel(objects,hds);
        return queryModel;
    }

    //刷新的方法
    public void updateTable(){
        table1.setModel(getTableModel());
    }

    public void createView() {
            JFrame frame = new JFrame("AdminWindow");
            frame.setContentPane(new AdminWindow().panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setSize(800,600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

    }

    //商品展示
    public DefaultTableModel getTableModel() {
        String [] hds = {"商品id","商品名","商品价格","商品库存"};
        FunctionDao functionDao = new FunctionDao();
        list = functionDao.selectView();
        Object[][] objects = new Object[list.size()][hds.length];
        for (int i=0; i<list.size(); i++){
            CommodityPojo commodityPojo = list.get(i);
            objects[i][0]= commodityPojo.getCommodity_ID();
            objects[i][1]= commodityPojo.getCommodity_name();
            objects[i][2]= commodityPojo.getCommodity_Price();
            objects[i][3]= commodityPojo.getCommodity_stock();
        }
        tableModel = new DefaultTableModel(objects, hds);
        return tableModel;
    }
    //table方法重写
    private void createUIComponents() {
        // TODO: place custom component creation code here
        table1 = new JTable(getTableModel());
    }
}
