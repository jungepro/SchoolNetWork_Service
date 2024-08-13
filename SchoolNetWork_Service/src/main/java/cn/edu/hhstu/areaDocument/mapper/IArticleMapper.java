package cn.edu.hhstu.areaDocument.mapper;

import cn.edu.hhstu.pojo.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Mapper
@Repository
public interface  IArticleMapper {

    public List<Article> list(HashMap params) throws Exception;

    public Article detail(String id) throws Exception;

    @Insert("insert into article (id,title,contents,articleClassId,publisher,createTime,creator,appId) values (#{id},#{title},#{contents},#{articleClassId},#{publisher},#{createTime},#{creator},#{appId})")
    public int insert(Article entity) throws Exception;

    @Update("update article set title=#{title},contents=#{contents},articleClassId=#{articleClassId},publisher=#{publisher},createTime=#{createTime} where id=#{id}")
    public int update(Article entity) throws Exception;

    @Delete("delete from article where id=#{id}")
    public int delete(String id) throws Exception;

    @Delete({
            "<script>",
            "delete from article where id in",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    public void deleteByIds(String[] ids) throws Exception;
}
