package cn.edu.scau.dao;

import cn.edu.scau.util.Response;
import cn.edu.scau.model.Area;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AreaDao {

    @Select("select * from area")
    List<Area> selectAll();

    @Insert("insert into area(name, address, status) values(#{name}, #{address}, #{status})")
    void insertOne(Area area);

    @Select("select name from area where id = #{area_id}")
    String selectAreaNameById(long area_id);

    @Delete("delete from area where id = #{id}")
    void deleteOneById(long id);

    @Update("update area set name = #{name}, address = #{address}, status = #{status} where id = #{id}")
    void updateOne(Area area);
}
