package cn.edu.scau.service.impl;

import cn.edu.scau.dao.QualityDao;
import cn.edu.scau.model.Quality;
import cn.edu.scau.service.IQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QualityServiceImpl implements IQualityService {

    @Autowired
    QualityDao qualityDao;

    @Override
    public int addOneInfo(Quality quality) {
        return qualityDao.insertOne(quality);
    }

    @Override
    public List<Quality> getAllQuality() {
        return qualityDao.selectAll();
    }

    @Override
    public int remove(List<Long> ids) {
        int i = 0;
        for (Long id : ids) {
            i += qualityDao.deleteById(id);
        }
        return i;
    }

    @Override
    public int updateOne(Quality quality) {
        return qualityDao.updateOneById(quality);
    }

    @Override
    public List<Quality> getQualityById(long id) {
        List<Quality> qualities = new ArrayList<>();
        qualities.add(qualityDao.selectOneById(id));
        return qualities;
    }

    @Override
    public List<Quality> getQualityByGoodsId(long id) {
        return qualityDao.getQualityByGoodsId(id);
    }
}
