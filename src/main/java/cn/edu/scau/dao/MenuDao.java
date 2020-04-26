package cn.edu.scau.dao;

import cn.edu.scau.model.Menu;
import cn.edu.scau.model.Meta;
import cn.edu.scau.model.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MenuDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    @Results(value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "url", property = "url", jdbcType = JdbcType.VARCHAR),
            @Result(column = "path", property = "path", jdbcType = JdbcType.VARCHAR),
            @Result(column = "component", property = "component", jdbcType = JdbcType.VARCHAR),
            @Result(column = "iconCls", property = "iconCls", jdbcType = JdbcType.VARCHAR),
            @Result(column = "parentId", property = "parentId", jdbcType = JdbcType.INTEGER),
            @Result(column = "enabled", property = "enabled", jdbcType = JdbcType.BIT),
            @Result(column = "id",property = "meta", javaType = Meta.class, one = @One(select = "selectMetaByPrimaryKey")),
//            @Result(column = "{parentId = id, userId = userId}",property = "children", javaType = List.class, many = @Many(select = "selectMenusByParentIdAndUserId"))
    })
    @Select("SELECT DISTINCT m.* FROM menu m, user_role ur, menu_role mr " +
            "WHERE ur.user_id = #{userId} AND ur.role_id = mr.role_id AND m.parentId = 1")
    List<Menu> getMenusByUserId(Integer userId);


    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "url", property = "url", jdbcType = JdbcType.VARCHAR),
            @Result(column = "path", property = "path", jdbcType = JdbcType.VARCHAR),
            @Result(column = "component", property = "component", jdbcType = JdbcType.VARCHAR),
            @Result(column = "iconCls", property = "iconCls", jdbcType = JdbcType.VARCHAR),
            @Result(column = "parentId", property = "parentId", jdbcType = JdbcType.INTEGER),
            @Result(column = "enabled", property = "enabled", jdbcType = JdbcType.BIT),
            @Result(column = "id",property = "meta", javaType = Meta.class, one = @One(select = "selectMetaByPrimaryKey")),
            @Result(column = "id", property = "roles", javaType = List.class,many = @Many(select = "selectRolesByMenuId"))
    })
    @Select("select distinct m.* from menu m, menu_role mr where m.id = mr.menu_id")
    List<Menu> getAllMenusWithRole();

    List<Menu> getAllMenus();

    List<Integer> getMidsByRid(Integer rid);

    @ResultType(Meta.class)
    @Select("select keepAlive, requireAuth from menu where id = #{id}")
    Meta selectMetaByPrimaryKey(Integer id);
//
//    @Select("SELECT m.* FROM menu m, menu_role mr, user_role ur " +
//            "WHERE m.parentId = #{parentId} AND ur.user_id = #{userId} AND ur.role_id = mr.role_id AND mr.menu_id = m.id AND m.enabled = TRUE")
//    List<Menu> selectMenusByParentIdAndUserId(Integer parentId, Integer userId);

    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "path", property = "path", jdbcType = JdbcType.VARCHAR),
            @Result(column = "component", property = "component", jdbcType = JdbcType.VARCHAR),
            @Result(column = "iconCls", property = "iconCls", jdbcType = JdbcType.VARCHAR),
            @Result(column = "parentId", property = "parentId", jdbcType = JdbcType.INTEGER),
            @Result(column = "enabled", property = "enabled", jdbcType = JdbcType.BIT),
            @Result(column = "id", property = "meta", javaType = Meta.class, one = @One(select = "selectMetaByPrimaryKey"))
    })
    @Select("SELECT m.id, m.path, m.component, m.iconCls, m.parentId, m.enabled, m.name FROM menu m, menu_role mr, user_role ur " +
            "WHERE m.parentId = #{parentId} AND ur.user_id = #{userId} AND ur.role_id = mr.role_id AND mr.menu_id = m.id AND m.enabled = TRUE order by m.id")
    List<Menu> selectMenusByParentIdAndUserId(Integer parentId, Integer userId);

    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name_zh", property = "nameZh", jdbcType = JdbcType.VARCHAR)
    })
    @Select("select r.* from menu_role mr, role r where mr.menu_id = #{menu_id} and r.id = mr.role_id")
    List<Role> selectRolesByMenuId(Integer menu_id);
}
