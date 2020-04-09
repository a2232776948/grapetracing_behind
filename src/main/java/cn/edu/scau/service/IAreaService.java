package cn.edu.scau.service;

import cn.edu.scau.model.Area;

import java.util.List;


public interface IAreaService {

    List<Area> getAllArea();

    void addArea(Area area);

    void removeAreas(long[] ids);

    void changeAreaInfo(Area area);
}
