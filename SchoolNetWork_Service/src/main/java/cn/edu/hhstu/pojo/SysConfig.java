package cn.edu.hhstu.pojo;


import org.springframework.format.annotation.DateTimeFormat;

public class SysConfig {

  private long id;
  private Integer appAuditYear;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  private java.util.Date appAuditStart;
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  private java.util.Date appAuditEnd;
  private boolean appAuditOpen;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public Integer getAppAuditYear() {
    return appAuditYear;
  }

  public void setAppAuditYear(Integer appAuditYear) {
    this.appAuditYear = appAuditYear;
  }


  public java.util.Date getAppAuditStart() {
    return appAuditStart;
  }

  public void setAppAuditStart(java.util.Date appAuditStart) {
    this.appAuditStart = appAuditStart;
  }


  public java.util.Date getAppAuditEnd() {
    return appAuditEnd;
  }

  public void setAppAuditEnd(java.util.Date appAuditEnd) {
    this.appAuditEnd = appAuditEnd;
  }

  public boolean isAppAuditOpen() {
    return appAuditOpen;
  }

  public void setAppAuditOpen(boolean appAuditOpen) {
    this.appAuditOpen = appAuditOpen;
  }
}
