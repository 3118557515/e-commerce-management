package com.yzdd.ec.pojo;
//这里是管理员类，定义管理员信息，封装待用
public class AdminPojo {
  //管理员账号
  private String admin_root;
  //管理员密码
  private  String admin_password;

  public AdminPojo() {
  }

  public AdminPojo(String admin_root, String admin_password) {
    this.admin_root = admin_root;
    this.admin_password = admin_password;
  }

  public String getAdmin_root() {
    return admin_root;
  }

  public void setAdmin_root(String admin_root) {
    this.admin_root = admin_root;
  }

  public String getAdmin_password() {
    return admin_password;
  }

  public void setAdmin_password(String admin_password) {
    this.admin_password = admin_password;
  }
}
