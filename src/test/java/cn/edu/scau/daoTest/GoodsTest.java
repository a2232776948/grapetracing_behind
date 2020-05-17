package cn.edu.scau.daoTest;

import cn.edu.scau.dao.*;
import cn.edu.scau.model.*;
import cn.edu.scau.service.IGoodsService;
import cn.edu.scau.service.LogisticService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsTest {

    @Autowired
    private LogisticService logisticService;

    @Autowired
    private LogisticDao logisticDao;

    @Autowired
    private CompanyDao companyDao;

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
        List<Goods> allGoods = goodsDao.getAllGoods();
        System.out.println(allGoods);
    }

    @Test
    public void addGoodsForTree(){
        Goods goods = new Goods(0,2,"新鲜葡萄","现摘葡萄",new Date(),"100000000000003",2);
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

    @Test
    public void getGoodsByQrId(){
        long id = goodsDao.getGoodsByQrId("goods100000000000027");
        System.out.println(id);
    }

    @Test
    public void getCompany(){
        Company company = companyDao.selectCompany();
        System.out.println(company);
    }

    @Test
    public void selectManyByCondition(){
        Date date = new Date();
        date.setYear(100);
        date.setMonth(2);
        date.setDate(12);
        SearchGoodsForm form = new SearchGoodsForm(22,3,"新鲜葡萄",date,new Date(),13);
        List<GoodsForm> goodsForms = goodsDao.selectManyByCondition(form);
        System.out.println(goodsForms);
    }

    @Test
    public void addLogistic() {
        Logistic logistic = new Logistic();
        logistic.setDate(new Date());
        logistic.setExpressNumber("232142343");
        long[] ids = {23,24};
        logistic.setGoodsIds(ids);
        logisticDao.addLogistic(logistic);
    }
    @Test
    public void delete(){
        long[] ids = {9,10};
        logisticService.delete(ids);
    }

}
