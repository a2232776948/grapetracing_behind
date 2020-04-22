package cn.edu.scau.daoTest;

import cn.edu.scau.dao.GoodsDao;
import cn.edu.scau.model.Goods;
import cn.edu.scau.model.GoodsQRcode;
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
}
