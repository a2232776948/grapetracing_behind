package cn.edu.scau.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tree {
    private long id;
    private long area_id;
    private String area;
    private String category;
    private Date plant_date;
    private String status;
    private String hash;
    private int count = 1;
}
