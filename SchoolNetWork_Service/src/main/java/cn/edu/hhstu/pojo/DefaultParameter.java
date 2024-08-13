package cn.edu.hhstu.pojo;


public class  DefaultParameter {

  private long id;
  private long deviceTypeId;
  private String paramName;
  private String unitName;
  private long orderNo;


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


  public String getParamName() {
    return paramName;
  }

  public void setParamName(String paramName) {
    this.paramName = paramName;
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

}
