package cn.edu.scau.service;

import cn.edu.scau.model.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IGoodsService {

    public List<Goods> getAllGoods();

    public boolean addGoodsForTree(Goods goods);

    public boolean addGoodsForArea(AddGoodsForm addGoodsForm);

    public int deleteGoods(List<Integer> ids);

    public boolean getGoodsQRcode(long id, HttpServletResponse response) throws Exception;

    public List<AreaGoodsCount> getAreaGoodsCount();

    public Goods getGoodsById(long id);

    public List<YearGoodsCount> getYearGoodsCount(long year);

    public List<GoodsForm> getGoodsForm();

    public List<GoodsForm> findGoodsForm(SearchGoodsForm form);

    public long getGoodsByQrId(String qrId);

    public String addGoodsQRcodes(long[] ids) throws Exception;

    public boolean getGoodsQRCodes(String url, HttpServletResponse response) throws Exception;

    public List<String> getCategory();

}
