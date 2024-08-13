package cn.edu.hhstu.pojo;


public class Room {

  private String id;
  private String roomName;
  private long co2Fire;
  private long powderFire;
  private long foamFire;
  private String remark;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }


  public long getCo2Fire() {
    return co2Fire;
  }

  public void setCo2Fire(long co2Fire) {
    this.co2Fire = co2Fire;
  }


  public long getPowderFire() {
    return powderFire;
  }

  public void setPowderFire(long powderFire) {
    this.powderFire = powderFire;
  }


  public long getFoamFire() {
    return foamFire;
  }

  public void setFoamFire(long foamFire) {
    this.foamFire = foamFire;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
