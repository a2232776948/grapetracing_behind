package cn.edu.scau.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsForm {
    private long id;
    private long treeId;
    private long areaId;
    private String category;
    private String desc;
    private Date date;
    private String  trackingNumber;
    @ApiModelProperty(notes = "查询模式，值可为（1-15，除6、7、14、15），按时间段查询为1，按地块查询为2，按植株查询为4，按分类查询为8，多条件查询则为条件对应值之和")
    private int mode;
}
