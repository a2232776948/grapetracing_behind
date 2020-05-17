package cn.edu.scau.service;

import cn.edu.scau.model.Quality;
import cn.edu.scau.model.SearchQualityForm;

import java.util.List;

public interface IQualityService {

    int addOneInfo(Quality quality);

    List<Quality> getAllQuality();

    int remove(List<Long> ids);

    int updateOne(Quality quality);

    List<Quality> getQualityById(long id);

    List<Quality> getQualityByGoodsId(long id);

    List<Quality> findQualities(SearchQualityForm form);

    List<String> getQualityCate();
}
