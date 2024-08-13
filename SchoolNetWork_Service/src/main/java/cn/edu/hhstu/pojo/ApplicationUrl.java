package cn.edu.hhstu.pojo;


public class  ApplicationUrl {

  private long id;
  private String applicationId;
  private String description;
  private String url;
  private String creator;
  private java.sql.Timestamp createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(String applicationId) {
    this.applicationId = applicationId;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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
