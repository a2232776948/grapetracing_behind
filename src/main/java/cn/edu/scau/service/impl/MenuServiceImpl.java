package cn.edu.scau.service.impl;

import cn.edu.scau.dao.MenuDao;
import cn.edu.scau.dao.MenuRoleDao;
import cn.edu.scau.model.Menu;
import cn.edu.scau.model.User;
import cn.edu.scau.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@CacheConfig(cacheNames = "menus_cache")
public class MenuServiceImpl implements IMenuService {
    @Autowired
    MenuDao menuDao;

    @Autowired
    MenuRoleDao menuRoleDao;

    public List<Menu> getMenuByUserId() {
        Integer userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Menu> menus = menuDao.getMenusByUserId(userId);
        for (Menu menu : menus) {
            menu.setChildren(menuDao.selectMenusByParentIdAndUserId(menu.getId(), userId));
        }
        return menus;
    }


    @Cacheable
    public List<Menu> getAllMenusWithRole() {
        return menuDao.getAllMenusWithRole();
    }

    public List<Menu> getAllMenus() {
        return menuDao.getAllMenus();
    }

    public List<Integer> getMidsByRid(Integer rid) {
        return menuDao.getMidsByRid(rid);
    }

    @Transactional
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleDao.deleteByRid(rid);
        if (mids == null || mids.length == 0) {
            return true;
        }
        Integer result = menuRoleDao.insertRecord(rid, mids);
        return result == mids.length;
    }

    @Override
    public List<Menu> getMenuByUserId2(Integer userId) {
        List<Menu> menus = menuDao.getMenusByUserId(userId);
        for (Menu menu : menus) {
            menu.setChildren(menuDao.selectMenusByParentIdAndUserId(menu.getId(), userId));
        }
        return menus;
    }
}
