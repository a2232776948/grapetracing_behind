package cn.edu.scau.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class SearchQualityForm {
    private long user_id;
    private String category;
    private Date start_date;
    private Date end_date;
    private long goods_id;
    @ApiModelProperty(notes = "查询模式，值可为（0-15），无条件为0，按时间段查询为1，按地块查询为2，按植株查询为4，按分类查询为8，多条件查询则为条件对应值之和")
    private int mode;
}
