package cn.edu.hhstu.pojo;


public class DeviceParameter {

  private long id;
  private long deviceTypeId;
  private String deviceId;
  private String paramName;
  private String paramValue;
  private String unitName;
  private long orderNo;
  private String creator;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getDeviceTypeId() {
    return deviceTypeId;
  }

  public void setDeviceTypeId(long deviceTypeId) {
    this.deviceTypeId = deviceTypeId;
  }


  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }


  public String getParamName() {
    return paramName;
  }

  public void setParamName(String paramName) {
    this.paramName = paramName;
  }


  public String getParamValue() {
    return paramValue;
  }

  public void setParamValue(String paramValue) {
    this.paramValue = paramValue;
  }


  public String getUnitName() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }


  public long getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(long orderNo) {
    this.orderNo = orderNo;
  }


  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }

}
