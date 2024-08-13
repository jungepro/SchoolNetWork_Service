package cn.edu.hhstu.areaStatistics.service;

import cn.edu.hhstu.entity.FigureEntity;

import java.util.List;

public interface IServerFigureService {
    public List<FigureEntity<Integer>> byTotal() throws Exception;
    public List<FigureEntity<Integer>> byServerYear() throws Exception;
    public List<FigureEntity<Integer>> byManufacturer() throws Exception;
    public List<FigureEntity<Integer>> byIsVm() throws Exception;
    public List<FigureEntity<Integer>> byIsTrust() throws Exception;
    public List<FigureEntity<Integer>> byVmServerYear() throws Exception;
    public List<FigureEntity<Integer>> byOs() throws Exception;
}
