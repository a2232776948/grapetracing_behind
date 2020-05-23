package cn.edu.scau.daoTest;

import cn.edu.scau.dao.MenuDao;
import cn.edu.scau.dao.QualityDao;
import cn.edu.scau.dao.TreeDao;
import cn.edu.scau.dao.UserDao;
import cn.edu.scau.model.*;
import cn.edu.scau.service.impl.FarmOPServiceImpl;
import cn.edu.scau.service.impl.TreeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TreeDaoTest {

    @Autowired
    QualityDao qualityDao;

    @Autowired
    MenuDao menuDao;

    @Autowired
    UserDao userDao;

    @Autowired
    FarmOPServiceImpl farmOPServiceImpl;

    @Autowired
    TreeDao treeDao;

    @Autowired
    TreeServiceImpl treeService;

    @Test
    public void test1() {
        List<Menu> menusByUserId = menuDao.getMenusByUserId(3);
        for (Menu menu : menusByUserId) {
            System.out.println(menu);
        }
    }

    @Test
    public void test2() {
        List<Menu> menus = menuDao.getAllMenusWithRole();
        for (Menu menu : menus) {
            System.out.println(menu);
        }
    }

    @Test
    public void test3() {
        int userId = 1;
        List<Menu> menus = menuDao.getMenusByUserId(userId);
        for (Menu menu : menus) {
            menu.setChildren(menuDao.selectMenusByParentIdAndUserId(menu.getId(), userId));
        }
        for (Menu menu : menus) {
            System.out.println(menu);
        }
    }

    @Test
    public void selectUsersWithRoles() {
        List<User> users = userDao.selectUsersWithRoles();
        System.out.println(users);
    }

    @Test
    public void updateById() {
        User user = new User();
        user.setId(1);
        user.setEnabled(true);
        user.setDetail("adfadfasdfdsaf");
        int i = userDao.updateById(user);
        System.out.println(i);
    }

    @Test
    public void insertUser(){
        User user = new User();
        user.setUsername("bois");
        user.setDetail("111111111111");
        user.setAge(22);
        user.setGender("男");
        user.setPhone("1231231321");
        userDao.insertUser(user);
    }

    @Test
    public void addOneFarmopForArea(){
        FarmOP farmOP = new FarmOP();
        farmOP.setTree_id(29);
        farmOP.setUser_id(3);
        farmOP.setDate(new Date());
        farmOP.setCategory("施肥");
        farmOP.setDesc("钾肥5kg");
        int b = farmOPServiceImpl.addOneFarmopForTree(farmOP);
        System.out.println(b);
    }

    @Test
    public void addOneFarmopForTree(){
        FarmOP farmOP = new FarmOP();
        farmOP.setTree_id(29);
        farmOP.setUser_id(3);
        farmOP.setDate(new Date());
        farmOP.setCategory("施肥");
        farmOP.setDesc("钾肥5kg");
        int b = farmOPServiceImpl.addOneFarmopForTree(farmOP);
        System.out.println(b);
    }


    @Test
    public void selectTreeById(){
        Tree tree = treeDao.selectTreeById(29);
        System.out.println(tree);
    }

    @Test
    public void findTrees(){
        SearchTreeForm form = new SearchTreeForm();
        form.setArea_id(3);
        form.setMode(2);
        List<Tree> trees = treeService.findTrees(form);
        for (Tree tree: trees) {
            System.out.println(tree);
        }
    }

    @Test
    public void banTest(){
        System.out.println("result="+(12 & 8));
    }

    @Test
    public void findQualities(){
        SearchQualityForm form = new SearchQualityForm();
        form.setMode(10);
        form.setGoods_id(23);
        form.setUser_id(3);
        List<Quality> qualities = qualityDao.findQualities(form);
        System.out.println(qualities);
    }

    @Test
    public void selectAllTest(){
        Quality qualities = qualityDao.selectAllTest();
        System.out.println(qualities);
    }

    @Test
    public  void selectAll(){
        List<Quality> qualities = qualityDao.selectAll();
        System.out.println(qualities);
    }

    @Test
    public  void selectOneById(){
        Quality quality = qualityDao.selectOneById(2);
        System.out.println(quality);
    }


}
