package cn.edu.scau.service;

import cn.edu.scau.model.*;

import java.util.List;

public interface IFarmOPService {
    List<FarmOP> getAllOPs();

    List<FarmOPCategory> getAllFarmCate();

    List<AreaFarmOPCount> selectAreaFarmOPCount();

    List<YearFarmopCount> selectYearFarmopCount(long year);

    boolean addOneFarmopForArea(FarmOP farmOP);

    int addOneFarmopForTree(FarmOP farmOP);

    int editOneFarmop(FarmOP farmOP);

    int delete(List<Long> ids);

    List<FarmOP> findFarmOPs(SearchFarmOPForm form);
}
