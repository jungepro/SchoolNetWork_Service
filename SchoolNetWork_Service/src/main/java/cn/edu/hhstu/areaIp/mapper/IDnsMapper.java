package cn.edu.hhstu.areaIp.mapper;

import cn.edu.hhstu.pojo.Dns;
import cn.edu.hhstu.pojo.IpDns;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 */
@Mapper
@Repository
public interface IDnsMapper {
    @Select({
            "<script>",
            "select dns.*,GROUP_CONCAT(pa.ip) ip from dns",
            "left join ip_dns pd on pd.dnsId=dns.Id",
            "left join ip_address pa on pa.Id=pd.IpAddressId",
            "<where>",
            "<if test='disabled!=null'>and disabled=#{disabled}</if>",
            "<if test='domainName!=null and domainName!=\"\"'>and domainName LIKE concat(\"%\",#{domainName},\"%\")</if>",
            "</where>",
            "GROUP BY dns.Id",
            "order by startDate desc",
            "</script>"
    })
    public List<Dns> list(String domainName, Integer disabled) throws Exception;

    @Select("select * from dns where id=#{id}")
    public Dns detail(int id) throws Exception;

    @Insert("insert into dns (domainName,domainDesc,remark,startDate,endDate,disabled) values (#{domainName},#{domainDesc},#{remark},#{startDate},#{endDate},#{disabled})")
    public int insert(Dns model) throws Exception;

    @Update("update dns set domainName=#{domainName},domainDesc=#{domainDesc},remark=#{remark},startDate=#{startDate},endDate=#{endDate},disabled=#{disabled} where id=#{id}")
    public int update(Dns model) throws Exception;

    @Delete("delete from dns where id=#{id}")
    public int delete(int id) throws Exception;

    @Update("update dns set disabled=1,endDate=#{endDate} where id=#{id}")
    public int offline(int id) throws Exception;

    @Update("update dns set disabled=0,endDate=null} where id=#{id}")
    public int online(int id) throws Exception;

    //ip未配置的dns
    @Select("select dns.* from dns " +
            "left join ip_dns pd on dns.Id= pd.DnsId " +
            "where dns.Disabled=0 and (IpAddressId<>#{ipAddressId} or IpAddressId is null) " +
            "order by DomainName")
    public List<Dns> listUnuseDns(int ipAddressId) throws Exception;

    //ip配置的dns
    @Select("select pd.*,dns.DomainName from ip_dns pd " +
            "left join dns on dns.Id= pd.DnsId " +
            "where IpAddressId=#{ipAddressId} " +
            "order by DomainName")
    public List<IpDns> listIpDns(int ipAddressId) throws Exception;
    //保存ip配置的dns
    @Insert("insert into ip_dns (dnsId,ipAddressId) values (#{dnsId},#{ipAddressId})")
    public int insertIpDns(Integer dnsId,Integer ipAddressId) throws Exception;
    //删除ip配置的dns
    @Delete("delete from ip_dns where dnsId=#{dnsId} and ipAddressId=#{ipAddressId}")
    public int deleteIpDns(int dnsId,int ipAddressId) throws Exception;
}
