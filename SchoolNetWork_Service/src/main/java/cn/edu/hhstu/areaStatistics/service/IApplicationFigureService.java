package cn.edu.hhstu.areaStatistics.service;

import cn.edu.hhstu.entity.FigureEntity;

import java.util.List;

public interface IApplicationFigureService {
    public List<FigureEntity<Integer>> byApplicationType() throws Exception;
    public List<FigureEntity<Integer>> byDepartment() throws Exception;
}

