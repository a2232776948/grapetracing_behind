package cn.edu.scau.dao;

import cn.edu.scau.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleDao {

    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name_zh", property = "nameZh", jdbcType = JdbcType.VARCHAR)
    })
    @Select("select * from role")
    List<Role> selectAllRoles();
}
