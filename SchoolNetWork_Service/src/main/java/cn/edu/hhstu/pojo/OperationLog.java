package cn.edu.hhstu.pojo;


public class OperationLog {

  private String id;
  private String operator;
  private long deviceTypeId;
  private String deviceName;
  private String deviceId;
  private String ip;
  private long operationLogTypeId;
  private String contents;
  private java.sql.Date operationDate;
  private String creator;
  private java.sql.Timestamp createTime;

  private OperationLogType operationLogType;
//  private DeviceType deviceType;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }


  public long getDeviceTypeId() {
    return deviceTypeId;
  }

  public void setDeviceTypeId(long deviceTypeId) {
    this.deviceTypeId = deviceTypeId;
  }


  public String getDeviceName() {
    return deviceName;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }


  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }


  public long getOperationLogTypeId() {
    return operationLogTypeId;
  }

  public void setOperationLogTypeId(long operationLogTypeId) {
    this.operationLogTypeId = operationLogTypeId;
  }


  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }


  public java.sql.Date getOperationDate() {
    return operationDate;
  }

  public void setOperationDate(java.sql.Date operationDate) {
    this.operationDate = operationDate;
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

  public OperationLogType getOperationLogType() {
    return operationLogType;
  }

  public void setOperationLogType(OperationLogType operationLogType) {
    this.operationLogType = operationLogType;
  }

//  public DeviceType getDeviceType() {
//    return deviceType;
//  }
//
//  public void setDeviceType(DeviceType deviceType) {
//    this.deviceType = deviceType;
//  }
}
