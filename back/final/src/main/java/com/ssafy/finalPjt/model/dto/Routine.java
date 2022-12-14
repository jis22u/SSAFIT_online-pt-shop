package com.ssafy.finalPjt.model.dto;

public class Routine {

  private int r_id;
  private String u_id;
  private String r_name;
  private int rd_id;
  private String v_id;
  private int li;
  private String title;

  public Routine() {
  }

  public Routine(int r_id, String u_id, String r_name, int rd_id, String v_id, int li, String title) {
    this.r_id = r_id;
    this.u_id = u_id;
    this.r_name = r_name;
    this.rd_id = rd_id;
    this.v_id = v_id;
    this.li = li;
    this.title = title;
  }

  public int getR_id() {
    return r_id;
  }

  public void setR_id(int r_id) {
    this.r_id = r_id;
  }

  public String getU_id() {
    return u_id;
  }

  public void setU_id(String u_id) {
    this.u_id = u_id;
  }

  public String getR_name() {
    return r_name;
  }

  public void setR_name(String r_name) {
    this.r_name = r_name;
  }

  public int getRd_id() {
    return rd_id;
  }

  public void setRd_id(int rd_id) {
    this.rd_id = rd_id;
  }

  public String getV_id() {
    return v_id;
  }

  public void setV_id(String v_id) {
    this.v_id = v_id;
  }

  public int getLi() {
    return li;
  }

  public void setLi(int li) {
    this.li = li;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Routine [r_id=" + r_id + ", u_id=" + u_id + ", r_name=" + r_name + ", rd_id=" + rd_id + ", v_id=" + v_id
        + ", li=" + li + ", title=" + title + "]";
  }

}
