package cn.edu.hhstu.pojo;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Dns {

  private long id;
  private String domainName;
  private String domainDesc;
  private String remark;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private java.util.Date startDate;
  private boolean disabled;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private java.util.Date endDate;

  //多个ip地址的拼接
  private String Ip;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDomainName() {
    return domainName;
  }

  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

  public String getDomainDesc() {
    return domainDesc;
  }

  public void setDomainDesc(String domainDesc) {
    this.domainDesc = domainDesc;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public boolean isDisabled() {
    return disabled;
  }

  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getIp() {
    return Ip;
  }

  public void setIp(String ip) {
    Ip = ip;
  }
}
