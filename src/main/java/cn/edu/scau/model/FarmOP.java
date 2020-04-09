package cn.edu.scau.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmOP {
    private long id;
    private long tree_id;
    private long area_id;
    private String category;
    private String desc;
    private Date date;
    private Integer user_id;
    private String opperson;
}
