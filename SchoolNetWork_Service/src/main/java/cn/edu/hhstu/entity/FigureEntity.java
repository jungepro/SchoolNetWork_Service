package cn.edu.hhstu.entity;

import lombok.Data;

@Data
public class  FigureEntity<T>{
    /// <summary>
    /// 范畴名称
    /// </summary>
    private  String subgroup;
    /// <summary>
    /// 分组名称
    /// </summary>
    private String name;
    /// <summary>
    /// 小类名称
    /// </summary>
    private  String kind;
    /// <summary>
    /// 统计值
    /// </summary>
    private T value;
    private T value1;
    private T value2;
}
