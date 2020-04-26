package cn.edu.scau.daoTest;

import cn.edu.scau.dao.FarmOPDao;
import cn.edu.scau.dao.GoodsDao;
import cn.edu.scau.dao.QualityDao;
import cn.edu.scau.model.*;
import cn.edu.scau.service.IGoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsTest {
    @Autowired
    private QualityDao qualityDao;
    
    @Autowired
    private FarmOPDao farmOPDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private IGoodsService goodsService;

    @Test
    public void getAllGoods(){
        List<GoodsQRcode> allGoods = goodsDao.getAllGoods();
        System.out.println(allGoods);
    }

    @Test
    public void getOneGoods(){
        GoodsQRcode allGoods = goodsDao.getOneGoods();
        System.out.println(allGoods);
    }

    @Test
    public void addGoodsForTree(){
        Goods goods = new Goods();
        Date date = new Date();
        goods.setDate(date);
        goods.setTreeId(3);
        goodsDao.addGoodsForTree(goods);
//        goodsDao.addGoodsForTree(goods);
    }

    @Test
    public void dateTest(){
        Date date = new Date();
        final long time = date.getTime();
        System.out.println(date);
    }

    @Test
    public void addGoodsForArea(){
        //goodsService.addGoodsForArea(2,new Date(),3);
    }

    @Test
    public void selectAreaFarmOPCount(){
        List<AreaFarmOPCount> areaFarmOPCounts = farmOPDao.selectAreaFarmOPCount();
        System.out.println(areaFarmOPCounts);
    }

    @Test
    public void getFarmopCountByDate(){
        //List<YearFarmopCount> farmopCountByDate = farmOPDao.getFarmopCountByDate(2019);
        //System.out.println(farmopCountByDate);
    }

    @Test
    public void getAreaGoodsCount() {
        List<AreaGoodsCount> areaGoodsCount = goodsDao.getAreaGoodsCount();
        System.out.println(areaGoodsCount);
    }

    @Test
    public void getYearGoodsCount(){
        List<YearGoodsCount> yearGoodsCounts = goodsDao.getYearGoodsCount(2020);
        System.out.println(yearGoodsCounts);
    }
    
    @Test
    public void getQualityByGoodsId(){
        List<Quality> qualityByGoodsId = qualityDao.getQualityByGoodsId(2);
        System.out.println(qualityByGoodsId);
    }

    @Test
    public void selectGoodsById(){
        Goods goods = goodsDao.selectGoodsById(2);
        System.out.println(goods);
    }

}
