package cn.edu.scau.dao;

import cn.edu.scau.model.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface FarmOPDao {

    @Results(id = "basemap",value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "tree_id", property = "tree_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "area_id", property = "area_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "category", property = "category", jdbcType = JdbcType.VARCHAR),
            @Result(column = "desc", property = "desc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "date", property = "date", jdbcType = JdbcType.DATE),
            @Result(column = "user_id", property = "user_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "user_id", property = "opperson", javaType = String.class, one = @One(select = "selectNameByUserId"))
    })
    @Select("select * from farmop")
    List<FarmOP> selectAllWithUsername();

    @Select("select name from user where id = #{id}")
    String selectNameByUserId(Integer id);


    @Results({
            @Result(column = "category", property = "category", jdbcType = JdbcType.VARCHAR)
    })
    @Select("select distinct category from farmop")
    List<FarmOPCategory> selectAllFarmCate();

    @Insert("insert into farmop(tree_id, area_id, category, farmop.desc, date, user_id) values(#{tree_id}, #{area_id}, #{category}, #{desc}, #{date, jdbcType=DATE}, #{user_id})")
    int insertOneFarmop(FarmOP farmOP);

    @Update("update farmop set category = #{category}, farmop.desc = #{desc}, date = #{date, jdbcType=DATE}, user_id = #{user_id} where tree_id = #{tree_id}")
    int updateOne(FarmOP farmOP);

    @Delete("delete from farmop where id = #{id}")
    int deleteById(long id);

    @ResultMap("basemap")
    @Select("select * from farmop where date >= #{start_date, jdbcType=DATE} and date <= #{end_date, jdbcType=DATE}")
    List<FarmOP> selectManyByPeriod(Date start_date, Date end_date);

    @ResultMap("basemap")
    @Select("select * from farmop where area_id = #{area_id}")
    List<FarmOP> selectManyByAreaId(long area_id);


    @ResultMap("basemap")
    @Select("select * from farmop where tree_id = #{tree_id}")
    List<FarmOP> selectManyByTreeId(long tree_id);


    @ResultMap("basemap")
    @Select("select * from farmop where user_id = #{user_id}")
    List<FarmOP> selectManyByUserId(int user_id);

    @ResultMap("basemap")
    @Select("select * from farmop where date >= #{start_date, jdbcType=DATE} " +
            "and date <= #{end_date, jdbcType=DATE} and user_id = #{user_id}")
    List<FarmOP> selectManyByPeriodAndUserId(Date start_date, Date end_date, int user_id);


    @ResultMap("basemap")
    @Select("select * from farmop where date >= #{start_date, jdbcType=DATE} " +
            "and date <= #{end_date, jdbcType=DATE} and area_id = #{area_id}")
    List<FarmOP> selectManyByPeriodAndAreaId(Date start_date, Date end_date, long area_id);

    @ResultMap("basemap")
    @Select("select * from farmop where date >= #{start_date, jdbcType=DATE} " +
            "and date <= #{end_date, jdbcType=DATE} and tree_id = #{tree_id}")
    List<FarmOP> selectManyByPeriodAndTreeId(Date start_date, Date end_date, long tree_id);

    @ResultMap("basemap")
    @Select("select * from farmop where area_id = #{area_id} and user_id = #{user_id}")
    List<FarmOP> selectManyByAreaIdAndUserId(long area_id, int user_id);

    @ResultMap("basemap")
    @Select("select * from farmop where date >= #{start_date, jdbcType=DATE} " +
            "and date <= #{end_date, jdbcType=DATE} and area_id = #{area_id} " +
            "and user_id = #{user_id}")
    List<FarmOP> selectManyByPeriodAndAreaIdAndUserId(Date start_date, Date end_date, long area_id, int user_id);

    @ResultMap("basemap")
    @Select("select * from farmop where tree_id = #{tree_id} and user_id = #{user_id}")
    List<FarmOP> selectManyByTreeIdAndUserId(long tree_id, int user_id);

    @ResultMap("basemap")
    @Select("select * from farmop where date >= #{start_date, jdbcType=DATE} " +
            "and date <= #{end_date, jdbcType=DATE} and tree_id = #{tree_id} " +
            "and user_id = #{user_id}")
    List<FarmOP> selectManyByPeriodAndTreeIdAndUserId(Date start_date, Date end_date, long tree_id, int user_id);

    @Select("SELECT area_id,area.`name`,COUNT(area_id) count from farmop,area  WHERE area.id=farmop.area_id group by area_id")
    List<AreaFarmOPCount> selectAreaFarmOPCount();

    @Select(" SELECT DATE_FORMAT(date,'%m') AS month,COUNT(*) count FROM farmop WHERE YEAR(date) = #{year} GROUP BY DATE_FORMAT(date,'%m')")
    List<YearFarmopCount> selectYearFarmopCount(long year);
}
