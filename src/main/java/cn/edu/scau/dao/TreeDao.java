package cn.edu.scau.dao;

import cn.edu.scau.model.Tree;
import cn.edu.scau.model.TreeCategory;
import com.github.pagehelper.ISelect;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface TreeDao {

    @Select("select count(*) from tree where area_id = #{area_id}")
    int countTreeByAreaId(@Param("area_id") long id);


    @Results({
            @Result(property = "plant_date", column = "plant_date", jdbcType = JdbcType.DATE),
            @Result(property = "area", column = "name")
    })
    @Select("select tree.id as id, area_id, area.name as name, category, plant_date, tree.status " +
            "from tree join area on tree.area_id = area.id")
    List<Tree> selectAll();

    @Results({
            @Result(property = "name", column = "category")
    })
    @Select("select distinct category from tree")
    List<TreeCategory> selectAllCategory();

    @Select("select count(*) from tree where category = #{name}")
    int selectCountByCategory(@Param("name") String name);

    @Select("select count(*) from tree where status = #{status, jdbcType=VARCHAR}")
    int selectCountByStatus(@Param("status") String status);

    @Insert("insert into tree(area_id, category, plant_date, status) " +
            "values(#{area_id}, #{category}, #{plant_date, jdbcType=DATE}, #{status})")
    void insertOne(Tree tree);

    @Select("call insertMany(" +
            "#{area_id,mode=IN}," +
            "#{category,mode=IN,jdbcType=VARCHAR}," +
            "#{plant_date,mode=IN,jdbcType=DATE}," +
            "#{status,mode=IN,jdbcType=VARCHAR}," +
            "#{count,mode=IN})")
    void insertMany(Tree tree);

    @Delete("delete from tree where id = #{id}")
    void deleteById(long id);


    @Update("update tree " +
            "set area_id = #{area_id}, category = #{category}, " +
            "plant_date = #{plant_date, jdbcType=DATE}, status = #{status}" +
            "where id = #{id}")
    void updateOneById(Tree tree);

    @Delete("delete from tree where area_id = #{id}")
    void deleteByAreaId(long id);

    @Results({
            @Result(property = "plant_date", column = "plant_date", jdbcType = JdbcType.DATE),
            @Result(property = "area", column = "name"),
            @Result(property = "id", column = "id"),
            @Result(property = "area_id", column = "area_id")

    })
    @Select("select tree.id as id, area_id, area.name as name, category, plant_date, tree.status " +
            "from tree join area on tree.area_id = area.id " +
            "where tree.id = #{id}")
    Tree selectTreeById(long id);

    @Results({
            @Result(property = "plant_date", column = "plant_date", jdbcType = JdbcType.DATE),
            @Result(property = "area", column = "name"),
    })
    @Select("select tree.id as id, area_id, area.name as name, category, plant_date, tree.status " +
            "from tree join area on tree.area_id = area.id where tree.area_id = #{area_id}")
    List<Tree> selectTreeByAreaId(long area_id);

    @Results({
            @Result(property = "plant_date", column = "plant_date", jdbcType = JdbcType.DATE),
            @Result(property = "area", column = "name"),
    })
    @Select("select tree.id as id, area_id, area.name as name, category, plant_date, tree.status " +
            "from tree join area on tree.area_id = area.id " +
            "where area_id = #{area_id} " +
            "and plant_date >= #{start_date, jdbcType=DATE} and plant_date <= #{end_date, jdbcType=DATE}")
    List<Tree> selectTreeByAreaIdAndPeriod(long area_id, Date start_date, Date end_date);

    @Results({
            @Result(property = "plant_date", column = "plant_date", jdbcType = JdbcType.DATE),
            @Result(property = "area", column = "name"),
    })
    @Select("select tree.id as id, area_id, area.name as name, category, plant_date, tree.status " +
            "from tree join area on tree.area_id = area.id " +
            "where tree.area_id = #{area_id} and tree.status = #{status}")
    List<Tree> selectTreeByAreaIdAndStatus(long area_id, String status);

    @Results({
            @Result(property = "plant_date", column = "plant_date", jdbcType = JdbcType.DATE),
            @Result(property = "area", column = "name"),
    })
    @Select("select tree.id as id, area_id, area.name as name, category, plant_date, tree.status " +
            "from tree join area on tree.area_id = area.id " +
            "where area_id = #{area_id} " +
            "and tree.status = #{status} and plant_date >= #{start_date, jdbcType=DATE} and plant_date <= #{end_date, jdbcType=DATE}")
    List<Tree> selectTreeByAreaIdAndPeriodAndStatus(long area_id,Date start_date,Date end_date, String status);

    @Results({
            @Result(property = "plant_date", column = "plant_date", jdbcType = JdbcType.DATE),
            @Result(property = "area", column = "name"),
    })
    @Select("select tree.id as id, area_id, area.name as name, category, plant_date, tree.status " +
            "from tree join area on tree.area_id = area.id " +
            "where tree.status = #{status} and plant_date >= #{start_date, jdbcType=DATE} and plant_date <= #{end_date, jdbcType=DATE}")
    List<Tree> selectTreeByStatusAndPeriod(String status, Date start_date, Date end_date);

    @Results({
            @Result(property = "plant_date", column = "plant_date", jdbcType = JdbcType.DATE),
            @Result(property = "area", column = "name"),
    })
    @Select("select tree.id as id, area_id, area.name as name, category, plant_date, tree.status " +
            "from tree join area on tree.area_id = area.id " +
            "where tree.status = #{status}")
    List<Tree> selectTreeByStatus(String status);

    @Results({
            @Result(property = "plant_date", column = "plant_date", jdbcType = JdbcType.DATE),
            @Result(property = "area", column = "name"),
    })
    @Select("select tree.id as id, area_id, area.name as name, category, plant_date, tree.status " +
            "from tree join area on tree.area_id = area.id " +
            "where plant_date >= #{start_date, jdbcType=DATE} and plant_date <= #{end_date, jdbcType=DATE}")
    List<Tree> selectTreeByPeriod(Date start_date, Date end_date);
}
