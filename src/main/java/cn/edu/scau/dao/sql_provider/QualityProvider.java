package cn.edu.scau.dao.sql_provider;

import cn.edu.scau.model.SearchGoodsForm;
import cn.edu.scau.model.SearchQualityForm;
import org.apache.ibatis.jdbc.SQL;

public class QualityProvider {
    public String findQualities(SearchQualityForm form) {
        String sql = new SQL() {
            {
                SELECT("quality.id,\n" +
                        "quality.goods_id,\n" +
                        "quality.category,\n" +
                        "quality.date,\n" +
                        "quality.description,\n" +
                        "quality.user_id\n");
                FROM("quality");
                WHERE("1=1");
                if((form.getMode() & 1) != 0 ){
                    WHERE("quality.date >= #{form.start_date, jdbcType=DATE} and quality.date <= #{form.end_date, jdbcType=DATE}");
                }if((form.getMode() & 2) != 0){
                    WHERE("quality.goods_id = #{form.goods_id}");
                }if((form.getMode() & 4) != 0){
                    WHERE("quality.category = #{form.category}");
                }if((form.getMode() & 8) != 0){
                    WHERE("quality.user_id = #{form.user_id}");
                }
            }
        }.toString();
        return sql;
    }
}
