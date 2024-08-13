package cn.edu.hhstu.pojo;


import java.sql.Timestamp;

public class IpAddress {

  private long id;
  private String ip;
  private long poolId;
  private int openType;
  private String remark;
  private String creator;
  private java.sql.Timestamp createTime;

  private String openTypeStr;

  private IpPool ipPool;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }


  public long getPoolId() {
    return poolId;
  }

  public void setPoolId(long poolId) {
    this.poolId = poolId;
  }

  public int getOpenType() {
    return openType;
  }

  public void setOpenType(int openType) {
    this.openType = openType;
  }

  public String getOpenTypeStr() {
    switch (openType){
      case 0:
        openTypeStr = "不开放";
        break;
      case 1:
        openTypeStr = "仅内网";
        break;
      case 2:
        openTypeStr = "内外网";
        break;
      default:
        openTypeStr = "未知";
        break;
    }
    return openTypeStr;
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


  public IpPool getIpPool() {
    return ipPool;
  }

  public void setIpPool(IpPool ipPool) {
    this.ipPool = ipPool;
  }

}
