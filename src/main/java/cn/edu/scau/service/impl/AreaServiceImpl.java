package cn.edu.scau.service.impl;

import cn.edu.scau.dao.AreaDao;
import cn.edu.scau.dao.TreeDao;
import cn.edu.scau.model.Area;
import cn.edu.scau.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private TreeDao treeDao;

    @Override
    public List<Area> getAllArea() {
        List<Area> areas = areaDao.selectAll();
        for (Area area : areas) {
            area.treeCount = treeDao.countTreeByAreaId(area.id);
        }
        return areas;
    }

    @Override
    public void addArea(Area area) {
        areaDao.insertOne(area);
    }

    @Override
    public void removeAreas(long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            treeDao.deleteByAreaId(ids[i]);
            areaDao.deleteOneById(ids[i]);
        }
    }

    @Override
    public void changeAreaInfo(Area area) {
        areaDao.updateOne(area);
    }
}
