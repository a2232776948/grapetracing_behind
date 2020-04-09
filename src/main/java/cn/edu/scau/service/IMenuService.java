package cn.edu.scau.service;

import cn.edu.scau.model.Menu;

import java.util.List;

public interface IMenuService {
    List<Menu> getMenuByUserId();

    List<Menu> getAllMenusWithRole();

    List<Menu> getAllMenus();

    List<Integer> getMidsByRid(Integer rid);

    boolean updateMenuRole(Integer rid, Integer[] mids);
}
