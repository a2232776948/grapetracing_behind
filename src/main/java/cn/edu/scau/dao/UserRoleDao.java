package cn.edu.scau.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRoleDao {

    @Delete("delete from user_role where user_id = #{userId}")
    void deleteByPrimaryKey(Integer userId);

    @Insert("<script>" +
            "insert into user_role(user_id, role_id) values" +
            "<foreach collection='rids' open='' item='rid' separator=',' close=''> (#{userId}, #{rid}) </foreach>" +
            "</script>")
    int addRole(Integer userId, Integer[] rids);
}
