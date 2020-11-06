package com.yzdd.ec.pojo;
//这是用户类，定义用户信息，封装待用
public class UserPojo {
  //用户名
  private String user_root;
  //用户密码
  private  String user_password;
  //用户注册时的手机号
  private  String user_TelephoneNumber;
  //用户注册时的邮箱
  private String user_email;

  public UserPojo() {
  }

  public UserPojo(String user_root, String user_password, String user_TelephoneNumber, String user_email) {
    this.user_root = user_root;
    this.user_password = user_password;
    this.user_TelephoneNumber = user_TelephoneNumber;
    this.user_email = user_email;
  }

  public String getUser_root() {
    return user_root;
  }

  public void setUser_root(String user_root) {
    this.user_root = user_root;
  }

  public String getUser_password() {
    return user_password;
  }

  public void setUser_password(String user_password) {
    this.user_password = user_password;
  }

  public String getUser_TelephoneNumber() {
    return user_TelephoneNumber;
  }

  public void setUser_TelephoneNumber(String user_TelephoneNumber) {
    this.user_TelephoneNumber = user_TelephoneNumber;
  }

  public String getUser_email() {
    return user_email;
  }

  public void setUser_email(String user_email) {
    this.user_email = user_email;
  }
}
