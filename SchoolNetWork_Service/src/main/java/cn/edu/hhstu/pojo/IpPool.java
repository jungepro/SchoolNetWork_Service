package cn.edu.hhstu.pojo;


public class  IpPool {

  private long id;
  private long domainId;
  private String ipSegment;
  private String mask;
  private String gateway;
  private String place;
  private String remark;
  private long ipNumber;
  private String creator;
  private java.sql.Timestamp createTime;

  private IpDomain ipDomain;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getDomainId() {
    return domainId;
  }

  public void setDomainId(long domainId) {
    this.domainId = domainId;
  }


  public String getIpSegment() {
    return ipSegment;
  }

  public void setIpSegment(String ipSegment) {
    this.ipSegment = ipSegment;
  }


  public String getMask() {
    return mask;
  }

  public void setMask(String mask) {
    this.mask = mask;
  }


  public String getGateway() {
    return gateway;
  }

  public void setGateway(String gateway) {
    this.gateway = gateway;
  }


  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public long getIpNumber() {
    return ipNumber;
  }

  public void setIpNumber(long ipNumber) {
    this.ipNumber = ipNumber;
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


  public IpDomain getIpDomain() {
    return ipDomain;
  }

  public void setIpDomain(IpDomain ipDomain) {
    this.ipDomain = ipDomain;
  }

}
