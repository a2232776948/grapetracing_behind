package cn.edu.scau.service;

import cn.edu.scau.model.AddGoodsForm;
import cn.edu.scau.model.AreaGoodsCount;
import cn.edu.scau.model.Goods;
import cn.edu.scau.model.GoodsQRcode;

import java.util.Date;
import java.util.List;

public interface IGoodsService {

    public List<GoodsQRcode> getAllGoods();

    public boolean addGoodsForTree(Goods goods);

    public boolean addGoodsForArea(AddGoodsForm addGoodsForm);

    public int deleteGoods(List<Integer> ids);

    public String getGoodsQRcode(Integer id) throws Exception;

    public List<AreaGoodsCount> getAreaGoodsCount();

    public Goods getGoodsById(long id);


}
