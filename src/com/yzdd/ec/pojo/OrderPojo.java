package com.yzdd.ec.pojo;
//这里是订单类，定义订单属性，封装待用
public class OrderPojo {
  private  String order_id;

  public String getOrder_id() {
    return order_id;
  }

  public void setOrder_id(String order_id) {
    this.order_id = order_id;
  }

  //下单用户的用户账号
  private String order_user;
  //用户下单时间
  private String order_datetime;
  //用户下单商品的名称
  private  String order_commodity;
  //用户下单商品的数量
  private int order_num;
  //送货地址
  private String order_address;
  //用户这一单的联系电话
  private String order_TelephoneNumber;

  public OrderPojo() {
  }

  public OrderPojo(String order_user, String order_datetime, String order_commodity, int order_num, String order_address, String order_TelephoneNumber) {
    this.order_user = order_user;
    this.order_datetime = order_datetime;
    this.order_commodity = order_commodity;
    this.order_num = order_num;
    this.order_address = order_address;
    this.order_TelephoneNumber = order_TelephoneNumber;
  }

  public String getOrder_user() {
    return order_user;
  }

  public void setOrder_user(String order_user) {
    this.order_user = order_user;
  }

  public String getOrder_datetime() {
    return order_datetime;
  }

  public void setOrder_datetime(String order_datetime) {
    this.order_datetime = order_datetime;
  }

  public String getOrder_commodity() {
    return order_commodity;
  }

  public void setOrder_commodity(String order_commodity) {
    this.order_commodity = order_commodity;
  }

  public int getOrder_num() {
    return order_num;
  }

  public void setOrder_num(int order_num) {
    this.order_num = order_num;
  }

  public String getOrder_address() {
    return order_address;
  }

  public void setOrder_address(String order_address) {
    this.order_address = order_address;
  }

  public String getOrder_TelephoneNumber() {
    return order_TelephoneNumber;
  }

  public void setOrder_TelephoneNumber(String order_TelephoneNumber) {
    this.order_TelephoneNumber = order_TelephoneNumber;
  }
}
