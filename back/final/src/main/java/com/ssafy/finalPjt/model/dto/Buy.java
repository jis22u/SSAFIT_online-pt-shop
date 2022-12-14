package com.ssafy.finalPjt.model.dto;

public class Buy {
  private int b_id;
  private String u_id;
  private String b_date;
  private int price_sum;
  private int d_id;

  private int bd_id;
  private String v_id;

  public Buy() {
  }

  public int getB_id() {
    return b_id;
  }

  public void setB_id(int b_id) {
    this.b_id = b_id;
  }

  public String getU_id() {
    return u_id;
  }

  public void setU_id(String u_id) {
    this.u_id = u_id;
  }

  public String getB_date() {
    return b_date;
  }

  public void setB_date(String b_date) {
    this.b_date = b_date;
  }

  public int getPrice_sum() {
    return price_sum;
  }

  public void setPrice_sum(int price_sum) {
    this.price_sum = price_sum;
  }

  public int getD_id() {
    return d_id;
  }

  public void setD_id(int d_id) {
    this.d_id = d_id;
  }

  public int getBd_id() {
    return bd_id;
  }

  public void setBd_id(int bd_id) {
    this.bd_id = bd_id;
  }

  public String getV_id() {
    return v_id;
  }

  public void setV_id(String v_id) {
    this.v_id = v_id;
  }

  @Override
  public String toString() {
    return "Buy [b_id=" + b_id + ", u_id=" + u_id + ", b_date=" + b_date + ", price_sum=" + price_sum + ", d_id=" + d_id
        + ", bd_id=" + bd_id + ", v_id=" + v_id + "]";
  }

}

