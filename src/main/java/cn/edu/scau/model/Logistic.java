package cn.edu.scau.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logistic {
    private long id;
    private long goodsId;
    private String expressNumber;
    private String status;
    private Date date;
    private long[] goodsIds;
}
