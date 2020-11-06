package com.yzdd.ec.pojo;
//这里是商品类，定义商品的属性，封装待用
public class CommodityPojo {
  //商品ID
  private int commodity_ID;
  //商品名称
  private String commodity_name;
  //商品的单价
  private String commodity_Price;
  //商品的库存
  private  String commodity_stock;

  public int getCommodity_ID() {
    return commodity_ID;
  }

  public void setCommodity_ID(int commodity_ID) {
    this.commodity_ID = commodity_ID;
  }

  public String getCommodity_name() {
    return commodity_name;
  }

  public void setCommodity_name(String commodity_name) {
    this.commodity_name = commodity_name;
  }

  public String getCommodity_Price() {
    return commodity_Price;
  }

  public void setCommodity_Price(String commodity_Price) {
    this.commodity_Price = commodity_Price;
  }

  public String getCommodity_stock() {
    return commodity_stock;
  }

  public void setCommodity_stock(String commodity_stock) {
    this.commodity_stock = commodity_stock;
  }

  public CommodityPojo() {
  }

  public CommodityPojo(int commodity_ID, String commodity_name, String commodity_Price, String commodity_stock) {
    this.commodity_ID = commodity_ID;
    this.commodity_name = commodity_name;
    this.commodity_Price = commodity_Price;
    this.commodity_stock = commodity_stock;
  }

  @Override
    public String toString() {
        return commodity_name+commodity_Price+commodity_stock;
    }
}
