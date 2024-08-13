package cn.edu.hhstu.pojo;


import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class  ApplicationAudit {

  private String id;
  private int annual;
  private String applicationId;
  private String appName;
  @DateTimeFormat(pattern = "yyyy-MM-dd") //前台传数据到后台
  private java.util.Date installTime;
  private long status;
  private String departmentId;
  private String departmentName;
  private String url;
  private String remark;
  private String auditor;
  private java.sql.Timestamp auditTime;

  List<String> urlList;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public int getAnnual() {
    return annual;
  }

  public void setAnnual(int annual) {
    this.annual = annual;
  }


  public String getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(String applicationId) {
    this.applicationId = applicationId;
  }


  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }


  public java.util.Date getInstallTime() {
    return installTime;
  }

  public void setInstallTime(java.util.Date installTime) {
    this.installTime = installTime;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public String getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(String departmentId) {
    this.departmentId = departmentId;
  }


  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getAuditor() {
    return auditor;
  }

  public void setAuditor(String auditor) {
    this.auditor = auditor;
  }


  public java.sql.Timestamp getAuditTime() {
    return auditTime;
  }

  public void setAuditTime(java.sql.Timestamp auditTime) {
    this.auditTime = auditTime;
  }

  public List<String> getUrlList() {
    if(urlList==null)
      urlList = new ArrayList<>();
    else
      urlList.clear();
    if(StringUtils.isNotBlank(this.url)){
      String[] str = this.url.split(",");
      for (int i=0;i<str.length;i++){
        urlList.add(str[i]);
      }
    }
    return urlList;
  }
}
