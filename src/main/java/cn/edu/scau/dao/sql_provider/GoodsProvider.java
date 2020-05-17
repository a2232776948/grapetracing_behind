package cn.edu.scau.dao.sql_provider;

import cn.edu.scau.model.SearchGoodsForm;
import org.apache.ibatis.jdbc.SQL;

public class GoodsProvider {
    public String selectManyByCondition(SearchGoodsForm form){
        String sql = new SQL() {
            {
                SELECT("goods.id,goods.tree_id,goods.category,goods.`desc`,goods.date,goods.tracking_number,tree.area_id");
                FROM("goods","tree");
                WHERE("goods.tree_id = tree.id");
                switch (form.getMode()) {
                    case 1:
                        WHERE("goods.date >= #{start_date, jdbcType=DATE} and goods.date <= #{end_date, jdbcType=DATE}");
                        break;
                    case 2:
                        WHERE("tree.area_id=#{area_id}");
                        break;
                    case 3:
                        WHERE("goods.date >= #{start_date, jdbcType=DATE} and goods.date <= #{end_date, jdbcType=DATE}");
                        WHERE("tree.area_id=#{area_id}");
                        break;
                    case 4:
                        WHERE("tree_id=#{tree_id}");
                        break;
                    case 5:
                        WHERE("goods.date >= #{start_date, jdbcType=DATE} and goods.date <= #{end_date, jdbcType=DATE}");
                        WHERE("tree_id=#{tree_id}");
                        break;
                    case 8:
                        WHERE("goods.category=#{category}");
                        break;
                    case 9:
                        WHERE("goods.date >= #{start_date, jdbcType=DATE} and goods.date <= #{end_date, jdbcType=DATE}");
                        WHERE("goods.category=#{category}");
                        break;
                    case 10:
                        WHERE("goods.category=#{category}");
                        WHERE("area_id=#{area_id}");
                        break;
                    case 11:
                        WHERE("goods.date >= #{start_date, jdbcType=DATE} and goods.date <= #{end_date, jdbcType=DATE}");
                        WHERE("goods.category=#{category}");
                        WHERE("area_id=#{area_id}");
                        break;
                    case 12:
                        WHERE("tree_id=#{tree_id}");
                        WHERE("goods.category=#{category}");
                        break;
                    case 13:
                        WHERE("goods.date >= #{start_date, jdbcType=DATE} and goods.date <= #{end_date, jdbcType=DATE}");
                        WHERE("goods.category=#{category}");
                        WHERE("tree_id=#{tree_id}");
                        break;
                    default:
                        break;
                }
            }
        }.toString();
        return sql;
    }
}
