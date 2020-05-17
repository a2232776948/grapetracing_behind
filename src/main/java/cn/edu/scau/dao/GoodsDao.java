package cn.edu.scau.dao;

import cn.edu.scau.dao.sql_provider.GoodsProvider;
import cn.edu.scau.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface GoodsDao {

    @Select("SELECT\n" +
            "goods.id,\n" +
            "goods.tree_id,\n" +
            "goods.category,\n" +
            "goods.`desc`,\n" +
            "goods.date,\n" +
            "goods.tracking_number\n" +
            "FROM\n" +
            "goods\n")
    public List<Goods> getAllGoods();

    @Select("call insertManyForGoodsByTree(#{treeId},#{category},#{desc},#{date},#{count})")
    public void addGoodsForTree( Goods goods);

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

    @Select("SELECT\n" +
            "goods.id,\n" +
            "goods.tree_id,\n" +
            "goods.category,\n" +
            "goods.`desc`,\n" +
            "goods.date,\n" +
            "goods.tracking_number,\n" +
            "tree.area_id\n" +
            "FROM\n" +
            "goods,tree\n" +
            "WHERE\n" +
            "goods.tree_id = tree.id\n")
    public List<GoodsForm> getGoodsForm();

    @Select("SELECT\n" +
            "goods.id\n" +
            "FROM\n" +
            "goods\n" +
            "WHERE\n" +
            "goods.tracking_number=#{qrId}")
    public long getGoodsByQrId(String qrId);

    //;;
    @SelectProvider(type = GoodsProvider.class,method = "selectManyByCondition")
    public List<GoodsForm> selectManyByCondition( SearchGoodsForm form);

    @Select("select DISTINCT(category) from goods")
    public List<String> getCategory();
}
