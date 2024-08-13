package cn.edu.hhstu.pojo;


import java.util.Date;

public class LogSys {

  private long id;
  private Date visitTime;
  private String userName;
  private String ip;
  private String url;
  private String requestMethod;
  private long executionTime;
  private String method;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public Date getVisitTime() {
    return visitTime;
  }

  public void setVisitTime(Date visitTime) {
    this.visitTime = visitTime;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getRequestMethod() {
    return requestMethod;
  }

  public void setRequestMethod(String requestMethod) {
    this.requestMethod = requestMethod;
  }

  public long getExecutionTime() {
    return executionTime;
  }

  public void setExecutionTime(long executionTime) {
    this.executionTime = executionTime;
  }


  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

}
