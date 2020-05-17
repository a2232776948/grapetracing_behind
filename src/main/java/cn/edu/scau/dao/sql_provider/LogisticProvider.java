package cn.edu.scau.dao.sql_provider;

import cn.edu.scau.model.Logistic;
import cn.edu.scau.model.SearchLogisticForm;
import org.apache.ibatis.jdbc.SQL;

public class LogisticProvider {
    public String addLogistic(Logistic logistic){
        StringBuffer sb = new StringBuffer();
        sb.append("insert into express(goods_id,express_number,status,date) values");
        for(long l:logistic.getGoodsIds()){
            String s = String.valueOf(l);
            sb.append("("+s+",#{expressNumber},#{status},#{date}),");
        }
        String sql = sb.substring(0,sb.length()-1);
        return sql;
    }

    public String delete(long[] ids){
        StringBuffer sb = new StringBuffer();
        sb.append("delete from express where id in(");
        for(long id:ids){
            String s = String.valueOf(id);
            sb.append(s+',');
        }
        String sql =sb.substring(0,sb.length()-1);
        sql+=')';
        return sql;
    }

    public String search(SearchLogisticForm form){
        return new SQL() {
            {
                SELECT("*");
                FROM("express");
                WHERE("1=1");
                if ((form.getMode() & 1) != 0) {
                    WHERE("express.date >= #{form.startDate, jdbcType=DATE} and express.date <= #{form.endDate, jdbcType=DATE}");
                }
                if ((form.getMode() & 2) != 0) {
                    WHERE("express.goods_id = #{form.goodsId}");
                }
                if ((form.getMode() & 4) != 0) {
                    WHERE("express.express_number = #{form.expressNumber}");
                }
                if ((form.getMode() & 8) != 0) {
                    WHERE("express.status = #{form.status}");
                }

            }
        }.toString();

    }
}
