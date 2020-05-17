package cn.edu.scau.dao;

import cn.edu.scau.dao.sql_provider.LogisticProvider;
import cn.edu.scau.model.Logistic;
import cn.edu.scau.model.SearchLogisticForm;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LogisticDao {
    @Select("SELECT\n" +
            "express.id,\n" +
            "express.goods_id,\n" +
            "express.express_number,\n" +
            "express.`status`,\n" +
            "express.date\n" +
            "FROM\n" +
            "express\n")
    public List<Logistic> getAllLogistic();

    @InsertProvider(type = LogisticProvider.class,method = "addLogistic")
    public int addLogistic(Logistic logistic);

    @InsertProvider(type = LogisticProvider.class,method = "delete")
    public int delete(@Param("ids") long[] ids);

    @SelectProvider(type=LogisticProvider.class,method = "search")
    public List<Logistic> search(@Param("form") SearchLogisticForm form);

    @Select("select DISTINCT(status) from express")
    public List<String>  getAllStatus();

    @Select("SELECT\n" +
            "express.id,\n" +
            "express.goods_id,\n" +
            "express.express_number,\n" +
            "express.`status`,\n" +
            "express.date\n" +
            "FROM\n" +
            "express\n" +
            "WHERE\n" +
            "express.`status` != '签收'\n")
    public List<Logistic> getLogiStaNoSign();

    @Update("UPDATE express set `status`=#{status} where id=#{id}")
    public int update(Logistic logistic);

}

