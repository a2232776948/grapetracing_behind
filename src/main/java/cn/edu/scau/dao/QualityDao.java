package cn.edu.scau.dao;

import cn.edu.scau.dao.sql_provider.QualityProvider;
import cn.edu.scau.model.Quality;
import cn.edu.scau.model.SearchQualityForm;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QualityDao {

    @Insert("insert into quality(goods_id, category, description, date, user_id) " +
            "values(#{goods_id}, #{category}, #{description}, #{date, jdbcType=DATE}, #{user_id})")
    int insertOne(Quality quality);

    @Results(id = "quality", value = {
            //@Result(column = "id", property = "id"),
            @Result(column = "id", property = "id", id = true),
            @Result(column = "goods_id", property = "goods_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "category", property = "category", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "date", property = "date", jdbcType = JdbcType.DATE),
            @Result(column = "user_id", property = "user_id", jdbcType = JdbcType.INTEGER),
            @Result(column = "user_id", property = "opperson", one = @One(select = "selectNameByUserId")),
    })
    @Select("select * from quality")
    List<Quality> selectAll();

    @ResultMap("quality")
    @Select("select * from quality where goods_id=23")
    Quality selectAllTest();


    @Select("select name from user where id = #{id}")
    String selectNameByUserId(int id);

    @Delete("delete from quality where id = #{id}")
    int deleteById(Long id);

    @Update("update quality " +
            "set goods_id = #{goods_id}, category = #{category}, " +
            "description = #{description}, date=#{date, jdbcType=DATE}, user_id= #{user_id} " +
            "where id = #{id}")
    int updateOneById(Quality quality);

    @ResultMap("quality")
    @Select("select * from quality where id = #{id}")
    Quality selectOneById(long id);

    @Select("SELECT\n" +
            "quality.id,\n" +
            "quality.goods_id,\n" +
            "quality.category,\n" +
            "quality.description,\n" +
            "quality.date,\n" +
            "quality.user_id\n" +
            "FROM\n" +
            "quality\n" +
            "WHERE\n" +
            "quality.goods_id = #{id}\n")
    List<Quality> getQualityByGoodsId(long id);

    @ResultMap("quality")
    @SelectProvider(type = QualityProvider.class,method = "findQualities")
    List<Quality> findQualities(@Param("form") SearchQualityForm form);

    @Select(" SELECT DISTINCT\n" +
            "(category)\n" +
            "FROM\n" +
            "quality\n")
    List<String> getQualityCate();
}
