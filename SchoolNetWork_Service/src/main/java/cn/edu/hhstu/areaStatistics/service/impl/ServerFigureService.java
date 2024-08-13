package cn.edu.hhstu.areaStatistics.service.impl;

import cn.edu.hhstu.areaStatistics.mapper.IServerFigureMapper;
import cn.edu.hhstu.areaStatistics.service.IServerFigureService;
import cn.edu.hhstu.entity.FigureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerFigureService implements IServerFigureService {

    @Autowired
    private IServerFigureMapper serverFigureMapper;

    @Override
    public List<FigureEntity<Integer>> byTotal() throws Exception {
        return serverFigureMapper.byTotal();
    }

    @Override
    public List<FigureEntity<Integer>> byServerYear() throws Exception {
        return serverFigureMapper.byServerYear();
    }

    @Override
    public List<FigureEntity<Integer>> byManufacturer() throws Exception {
        return serverFigureMapper.byManufacturer();
    }

    @Override
    public List<FigureEntity<Integer>> byIsVm() throws Exception {
        return serverFigureMapper.byIsVm();
    }

    @Override
    public List<FigureEntity<Integer>> byIsTrust() throws Exception {
        return serverFigureMapper.byIsTrust();
    }

    @Override
    public List<FigureEntity<Integer>> byVmServerYear() throws Exception {
        return serverFigureMapper.byVmServerYear();
    }

    @Override
    public List<FigureEntity<Integer>> byOs() throws Exception {
        return serverFigureMapper.byOs();
    }
}
