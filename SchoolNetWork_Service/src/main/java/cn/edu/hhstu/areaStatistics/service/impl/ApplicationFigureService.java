package cn.edu.hhstu.areaStatistics.service.impl;

import cn.edu.hhstu.areaStatistics.mapper.IApplicationFigureMapper;
import cn.edu.hhstu.areaStatistics.service.IApplicationFigureService;
import cn.edu.hhstu.entity.FigureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationFigureService implements IApplicationFigureService {

    @Autowired
    private IApplicationFigureMapper applicationFigureMapper;

    @Override
    public List<FigureEntity<Integer>> byApplicationType() throws Exception {
        return applicationFigureMapper.byApplicationType();
    }

    @Override
    public List<FigureEntity<Integer>> byDepartment() throws Exception {
        return applicationFigureMapper.byDepartment();
    }
}
