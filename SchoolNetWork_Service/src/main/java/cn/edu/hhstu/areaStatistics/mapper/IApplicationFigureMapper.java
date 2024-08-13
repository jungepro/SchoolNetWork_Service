package cn.edu.hhstu.areaStatistics.mapper;

import cn.edu.hhstu.entity.FigureEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface  IApplicationFigureMapper {

    //应用类型
    @Select("select IFNULL(ApplicationTypeName,'未知') 'name',count(0) 'value' from application ap " +
            "left join application_type tp on tp.Id=ap.ApplicationTypeId " +
            "where `Status`=1 and Pid=0 " +
            "group by tp.ApplicationTypeName " +
            "order by `value` desc")
    public List<FigureEntity<Integer>> byApplicationType() throws Exception;

    //运维单位
    @Select("select IFNULL(DepartmentName,'未知') 'name',count(0) 'value' from application ap " +
            "left join department dp on dp.Id=ap.DepartmentId " +
            "where `Status`=1 and Pid=0 " +
            "group by DepartmentName " +
            "order by `value` desc ")
    public List<FigureEntity<Integer>> byDepartment() throws Exception;

}
