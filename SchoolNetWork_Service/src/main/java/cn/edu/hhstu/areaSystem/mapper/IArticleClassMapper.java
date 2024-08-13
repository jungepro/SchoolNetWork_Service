package cn.edu.hhstu.areaSystem.mapper;

import cn.edu.hhstu.pojo.ArticleClass;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.xml.soap.Detail;
import java.util.List;

@Mapper
@Repository
public interface  IArticleClassMapper {

    @Select({
            "<script>",
            "select * from article_class",
            "<where>",
            "<if test='category!=null'>category=#{category}</if>",
            "</where>",
            "</script>"
    })
    public List<ArticleClass> list(Integer category) throws Exception;
    public List<ArticleClass> list() throws Exception;

    @Select("select * from article_class where id=#{id}")
    public ArticleClass detail(String id) throws Exception;

    @Insert("insert into article_class (id,className,category,orderNo,shown) values (#{id},#{className},#{category},#{orderNo},#{shown})")
    public int insert(ArticleClass entity) throws Exception;

    @Update("update article_class set className=#{className},category=#{category},orderNo=#{orderNo},shown=#{shown} where id=#{id}")
    public int update(ArticleClass entity) throws Exception;

    @Delete("delete from article_class where id=#{id}")
    public int delete(String id) throws Exception;
}
