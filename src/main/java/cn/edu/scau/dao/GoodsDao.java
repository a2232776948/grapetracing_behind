package cn.edu.scau.dao;

import cn.edu.scau.model.AreaGoodsCount;
import cn.edu.scau.model.Goods;
import cn.edu.scau.model.GoodsQRcode;
import cn.edu.scau.model.YearGoodsCount;
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

    @Select("SELECT\n" +
            "area.id,\n" +
            "area.`name` ,\n" +
            "Count(area.id) AS count\n" +
            "FROM goods,area,tree\n" +
            "where goods.tree_id = tree.id and area.id = tree.area_id\n" +
            "GROUP BY area.id\n")
    public List<AreaGoodsCount> getAreaGoodsCount();

    @Select("SELECT\n" +
            "\tDATE_FORMAT(date, '%m') AS MONTH,\n" +
            "\tCOUNT(*) count\n" +
            "FROM\n" +
            "\tgoods\n" +
            "WHERE\n" +
            "\tYEAR (date) = #{year}\n" +
            "GROUP BY DATE_FORMAT(date,'%m')")
    public List<YearGoodsCount> getYearGoodsCount(long year);

    @Select("SELECT\n" +
            "goods.id,\n" +
            "goods.tree_id,\n" +
            "goods.date\n" +
            "FROM\n" +
            "goods\n" +
            "WHERE\n" +
            "goods.id = #{id}\n")
    public Goods selectGoodsById(long id);

}
