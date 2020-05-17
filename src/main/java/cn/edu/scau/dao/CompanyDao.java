package cn.edu.scau.dao;

import cn.edu.scau.model.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CompanyDao {

    @Select("select * from company")
    public Company selectCompany();
}
