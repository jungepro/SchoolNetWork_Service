package cn.edu.hhstu.pojo;


import cn.edu.hhstu.entity.DeviceEntity;
import org.springframework.format.annotation.DateTimeFormat;

public class  Application {

  private String id;
  private String pid;
  private String deviceId;
  private long applicationTypeId;
  private String appName;
  private String version;
  @DateTimeFormat(pattern = "yyyy-MM-dd") //前台传数据到后台
  private java.util.Date installTime;
  private String company;
  private String contact;
  private String departmentId;
  private String linkMan;
  private String phone;
  private String email;
  private String port;
  private boolean record;
  private long level;
  private long status;
  private String remark;
  private String creator;
  private java.sql.Timestamp createTime;

  private Department department;
  private ApplicationType applicationType;
  private DeviceEntity deviceEntity;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }


  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }


  public long getApplicationTypeId() {
    return applicationTypeId;
  }

  public void setApplicationTypeId(long applicationTypeId) {
    this.applicationTypeId = applicationTypeId;
  }


  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }


  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }


  public java.util.Date getInstallTime() {
    return installTime;
  }

  public void setInstallTime(java.util.Date installTime) {
    this.installTime = installTime;
  }


  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }


  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }


  public String getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(String departmentId) {
    this.departmentId = departmentId;
  }


  public String getLinkMan() {
    return linkMan;
  }

  public void setLinkMan(String linkMan) {
    this.linkMan = linkMan;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }


  public boolean getRecord() {
    return record;
  }

  public void setRecord(boolean record) {
    this.record = record;
  }


  public long getLevel() {
    return level;
  }

  public void setLevel(long level) {
    this.level = level;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
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

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public ApplicationType getApplicationType() {
    return applicationType;
  }

  public void setApplicationType(ApplicationType applicationType) {
    this.applicationType = applicationType;
  }

  public DeviceEntity getDeviceEntity() {
    return deviceEntity;
  }

  public void setDeviceEntity(DeviceEntity deviceEntity) {
    this.deviceEntity = deviceEntity;
  }
}
