package com.yzdd.ec.ui;

import com.yzdd.ec.dao.AddCart;
import com.yzdd.ec.dao.ShowCommodity;
import com.yzdd.ec.dao.ShowUserName;
import com.yzdd.ec.pojo.CommodityPojo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class UserWindow {
  private JPanel jpanel1;
  private JLabel userName;
  private JLabel exitLogin;
  private JLabel shopp;
  private JLabel order;
  private JPanel topJPanel;
  private JPanel bottomJPanel;
  private JPanel leftJPanel;
  private JPanel rightJPanel;
  private JPanel centerJPanel;
  private JScrollPane rollPane;
  private JPanel jRollPanel;
  private  JFrame frame;
  private  String user_id;
  private String user_root;

  public UserWindow(String user_id,String user_root) {
    this.user_id=user_id;
    this.user_root=user_root;
    userName.setText(user_root+"(个人中心)");
    userName.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        frame.setVisible(false);
        PersonalCenter personalCenter=new PersonalCenter(user_id,user_root);
        personalCenter.createView();
      }
    });
    exitLogin.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int a=JOptionPane.showConfirmDialog(null,"是否要退出?");
        if (a==JOptionPane.YES_OPTION){
          frame.setVisible(false);
          LoginWindow loginWindow=new LoginWindow();
          loginWindow.CreateView();
        }
      }
    });
    shopp.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        frame.setVisible(false);
        ShoppingCartWindow shoppingCartWindow=new ShoppingCartWindow(user_id,user_root);
        shoppingCartWindow.createView();
      }
    });
    order.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        frame.setVisible(false);
        OrderWindow orderWindow = new OrderWindow(user_id,user_root);
        orderWindow.createView();
      }
    });
  }


  private void createUIComponents() {
    // TODO: place custom component creation code here
    jRollPanel = new JPanel(new GridLayout(0,6,30,20));
  rollPane = new JScrollPane(jRollPanel);
  rollPane.setPreferredSize(new Dimension(1200,800));
  rollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    ShowCommodity showCommodity=new ShowCommodity();
    List<CommodityPojo> list =showCommodity.selectCommodity();
    for (int i = 0; i < list.size(); i++) {
      JPanel jPanel = new JPanel(new GridLayout(2,1));
      String name=list.get(i).getCommodity_name();
      String price=list.get(i).getCommodity_Price();
      JLabel show = new JLabel("<html>"+name+"<br>价格："+price+"</html>");
      jPanel.add(show);
      JButton jButton=new JButton("加入购物车");

      jButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          super.mouseClicked(e);
          AddCart addCart=new AddCart();
          boolean answer=addCart.AddCart(user_id,name,1, Integer.parseInt(price));
          if (answer){
            JOptionPane.showMessageDialog(null,"加购成功!");
          }else {
            JOptionPane.showMessageDialog(null,"对不起,货物不足");
          }
        }
      });

      jPanel.add(jButton);
      jRollPanel.add(jPanel);
    }

  }

  public  void createView(){
    frame = new JFrame("用户窗口");
    frame.setContentPane(jpanel1);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
//    GraphicsDevice gd=ge.getDefaultScreenDevice();
//    gd.setFullScreenWindow(frame);

    frame.setSize(1200 ,800);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
//    this.showAll();
  }



}
