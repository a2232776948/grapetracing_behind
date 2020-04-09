package cn.edu.scau.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quality {
    private long id;
    private long goods_id;
    private String category;
    private String description;
    private Date date;
    private int user_id;
    private String opperson;
}
