package cn.edu.scau.dao;

import cn.edu.scau.model.Role;
import cn.edu.scau.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    String Base_Column_list =
            "id, name, phone, age, detail, date, gender, " +
                    "username, password, userface, enabled, hash";

    @Results(id = "UserMap", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.VARCHAR),
            @Result(column = "date", property = "date", jdbcType = JdbcType.DATE),
            @Result(column = "age", property = "age", jdbcType = JdbcType.INTEGER),
            @Result(column = "userface", property = "userface", jdbcType = JdbcType.VARCHAR),
            @Result(column = "detail", property = "detail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "hash", property = "hash", jdbcType = JdbcType.VARCHAR),
            @Result(column = "enabled", property = "enabled", jdbcType = JdbcType.BIT)
    })
    @Select("select " + Base_Column_list + " from user where id = #{userId, jdbcType=INTEGER}")
    User selectByPrimaryKey(Integer userId);

    @ResultMap("UserMap")
    @Select("select * from user where username = #{username}")
    User loadUserByUsername(String username);

    @ResultType(value = Role.class)
    @Select("select r.* from role r, user_role ur where ur.role_id = r.id and ur.user_id = #{id}")
    List<Role> getUserRolesById(Integer id);

    @Delete("delete from user where id = #{id, jdbcType=INTEGER}")
    Integer deleteByPrimaryKey(Integer id);


    @Update("update user set password = #{encodePass} where id = #{userId}")
    Integer updatePasswd(Integer userId, String encodePass);

    @Update("update user set userface = #{url} where id=#{id}")
    Integer updateUserface(String url, Integer id);


    @Results(value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.VARCHAR),
            @Result(column = "date", property = "date", jdbcType = JdbcType.DATE),
            @Result(column = "age", property = "age", jdbcType = JdbcType.INTEGER),
            @Result(column = "userface", property = "userface", jdbcType = JdbcType.VARCHAR),
            @Result(column = "detail", property = "detail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "hash", property = "hash", jdbcType = JdbcType.VARCHAR),
            @Result(column = "enabled", property = "enabled", jdbcType = JdbcType.BIT),
            @Result(column = "id", property = "roles", javaType = List.class, many = @Many(select = "getUserRolesById"))
    })
    @Select("select * from user order by id")
    List<User> selectUsersWithRoles();

    @Results(value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.VARCHAR),
            @Result(column = "date", property = "date", jdbcType = JdbcType.DATE),
            @Result(column = "age", property = "age", jdbcType = JdbcType.INTEGER),
            @Result(column = "userface", property = "userface", jdbcType = JdbcType.VARCHAR),
            @Result(column = "detail", property = "detail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "hash", property = "hash", jdbcType = JdbcType.VARCHAR),
            @Result(column = "enabled", property = "enabled", jdbcType = JdbcType.BIT),
            @Result(column = "id", property = "roles", javaType = List.class, many = @Many(select = "getUserRolesById"))
    })
    @Select("select * from user where name like concat('%',#{keywords},'%') order by id")
    List<User> selectUserWithRolesByKeywords(String keywords);


    @Update("<script>update user\n" +
            "        <set>\n" +
            "            <if test=\"name != null\">\n" +
            "                name = #{name,jdbcType=VARCHAR},\n" +
            "            </if>\n" +
            "            <if test=\"username != null\">\n" +
            "                username = #{username,jdbcType=VARCHAR},\n" +
            "            </if>\n" +
            "            <if test=\"password != null\">\n" +
            "                password = #{password,jdbcType=VARCHAR},\n" +
            "            </if>\n" +
            "            <if test=\"phone != null\">\n" +
            "                phone = #{phone,jdbcType=CHAR},\n" +
            "            </if>\n" +
            "            <if test=\"gender != null\">\n" +
            "                gender = #{gender,jdbcType=VARCHAR},\n" +
            "            </if>\n" +
            "            <if test=\"date != null\">\n" +
            "                date = #{date, jdbcType=DATE},\n" +
            "            </if>\n" +
            "            <if test=\"age != null\">\n" +
            "                age = #{age, jdbcType=INTEGER},\n" +
            "            </if>\n" +
            "            <if test=\"enabled != null\">\n" +
            "                enabled = #{enabled,jdbcType=BIT},\n" +
            "            </if>\n" +
            "            <if test=\"userface != null\">\n" +
            "                userface = #{userface,jdbcType=VARCHAR},\n" +
            "            </if>\n" +
            "            <if test=\"detail != null\">\n" +
            "                detail = #{detail,jdbcType=VARCHAR},\n" +
            "            </if>\n" +
            "            <if test=\"hash != null\">\n" +
            "                hash = #{hash,jdbcType=VARCHAR},\n" +
            "            </if>\n" +
            "        </set>\n" +
            "        where id = #{id,jdbcType=INTEGER}" +
            "</script>")
    int updateById(User user);

    @Insert("insert into user(name, phone, age, detail, date, gender, username, password, userface, enabled, hash) " +
            "values(#{name}, #{phone}, #{age}, #{detail}, #{date, jdbcType=DATE}, #{gender}, #{username}, #{password}, #{userface}, #{enabled}, #{hash})")
    int insertUser(User user);
}
