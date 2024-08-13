package cn.edu.hhstu.pojo;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class IpIsp {

  private long id;
  private String ispNo;
  private String ispName;
  private String contacts;
  private String phone;
  @DateTimeFormat(pattern = "yyyy-MM-dd") //前台传数据到后台
  private Date startTime;
  private String remark;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getIspNo() {
    return ispNo;
  }

  public void setIspNo(String ispNo) {
    this.ispNo = ispNo;
  }


  public String getIspName() {
    return ispName;
  }

  public void setIspName(String ispName) {
    this.ispName = ispName;
  }


  public String getContacts() {
    return contacts;
  }

  public void setContacts(String contacts) {
    this.contacts = contacts;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
