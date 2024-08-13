package cn.edu.hhstu.areaStatistics.mapper;

import cn.edu.hhstu.entity.FigureEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IServerFigureMapper {
    //物理和虚机总量
    @Select("select '物理机' as 'name',count(0) 'value' from device dv " +
            "left join device_batch db on db.Id= dv.DeviceBatchId " +
            "where db.DeviceTypeId=1 and dv.`Status`=1 " +
            "union " +
            "select '虚机' as 'name',count(0) 'value' from vm_server where `Status`=1")
    public List<FigureEntity<Integer>> byTotal() throws Exception;
    //物理服务器年度入库
    @Select("select IFNULL(YEAR(InTime),'未知') 'name',sum(Amount) 'value' from device_batch " +
            "where DeviceTypeId=1 " +
            "group by `name` " +
            "order by `name`")
    public List<FigureEntity<Integer>> byServerYear() throws Exception;
    //物理机厂商
    @Select("select IFNULL(m.ManufacturerName,'未知') 'name',count(0) 'value' from device  dv " +
            "LEFT JOIN device_batch db on dv.DeviceBatchId=db.Id " +
            "left join manufacturer m on m.Id=db.ManufacturerId " +
            "where db.DeviceTypeId=1 and dv.`Status`=1 " +
            "GROUP BY  m.ManufacturerName " +
            "order by `value` desc")
    public List<FigureEntity<Integer>> byManufacturer() throws Exception;
    //虚拟化比例
    @Select("select case when IsVm=1 then '是' else '否' end 'name', COUNT(0) 'value' from device dv " +
            "left join device_batch db on db.Id= dv.DeviceBatchId " +
            "where db.DeviceTypeId=1 and dv.`Status`=1 group by IsVm")
    public List<FigureEntity<Integer>> byIsVm();
    //托管
    @Select("select case when IsTrust=1 then '是' else '否' end 'name', COUNT(0) 'value' from device dv " +
            "left join device_batch db on db.Id= dv.DeviceBatchId " +
            "where db.DeviceTypeId=1 and dv.`Status`=1 group by IsTrust")
    public List<FigureEntity<Integer>> byIsTrust() throws Exception;
    //虚拟服务器创建
    @Select("select IFNULL(YEAR(InTime),'未知') 'name',count(0) 'value' from vm_server " +
            "where `Status`=1 " +
            "group by `name` " +
            "order by `name`")
    public List<FigureEntity<Integer>> byVmServerYear() throws Exception;
    //操作系统
    @Select("select IFNULL(OSName,'未知') 'name',count(0) 'value' from " +
            "(select os.OSName from device dv " +
            "left join device_batch db on db.Id= dv.DeviceBatchId " +
            "left join os on dv.OsId=os.Id " +
            "where db.DeviceTypeId=1 and dv.`Status`=1 " +
            "union all " +
            "select OSName from vm_server vs " +
            "left join os on vs.OsId=os.Id " +
            "where `Status`=1) tbl " +
            "GROUP BY OSName " +
            "order by `value` desc")
    public List<FigureEntity<Integer>> byOs() throws Exception;


}
