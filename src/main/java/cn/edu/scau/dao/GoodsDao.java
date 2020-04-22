package cn.edu.scau.dao;

import cn.edu.scau.model.Goods;
import cn.edu.scau.model.GoodsQRcode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsDao {

    @Select("SELECT goods.id,area_id,tree_id,date FROM goods,tree WHERE tree_id=tree.id")
    public List<GoodsQRcode> getAllGoods();

    @Select("SELECT goods.id,area_id,tree_id,date FROM goods,tree WHERE tree_id=tree.id and goods.id=2")
    public GoodsQRcode getOneGoods();

    @Select("call insertManyForGoodsByTree(#{goods.treeId},#{goods.date},#{goods.count})")
    public void addGoodsForTree(@Param("goods") Goods goods);

    public int deleteGoods(List<Integer> ids);

}
