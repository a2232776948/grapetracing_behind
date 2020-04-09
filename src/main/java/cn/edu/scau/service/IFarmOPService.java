package cn.edu.scau.service;

import cn.edu.scau.model.FarmOP;
import cn.edu.scau.model.FarmOPCategory;
import cn.edu.scau.model.SearchFarmOPForm;

import java.util.List;

public interface IFarmOPService {
    List<FarmOP> getAllOPs();

    List<FarmOPCategory> getAllFarmCate();

    boolean addOneFarmopForArea(FarmOP farmOP);

    int addOneFarmopForTree(FarmOP farmOP);

    int editOneFarmop(FarmOP farmOP);

    int delete(List<Long> ids);

    List<FarmOP> findFarmOPs(SearchFarmOPForm form);
}
