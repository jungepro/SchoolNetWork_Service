package cn.edu.hhstu.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

public class  Article {

  private String id;
  private String title;
  private String contents;
  private String articleClassId;
  private String publisher;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private java.sql.Timestamp createTime;
  private String creator;
  private String appId;

  private ArticleClass articleClass;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }


  public String getArticleClassId() {
    return articleClassId;
  }

  public void setArticleClassId(String articleClassId) {
    this.articleClassId = articleClassId;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }


  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public ArticleClass getArticleClass() {
    return articleClass;
  }

  public void setArticleClass(ArticleClass articleClass) {
    this.articleClass = articleClass;
  }
}
