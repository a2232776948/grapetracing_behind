package cn.edu.scau.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("农事操作搜索条件表")
public class SearchFarmOPForm {
    private long area_id;
    private Date start_date;
    private Date end_date;
    private long tree_id;
    private int user_id;
    @ApiModelProperty(notes = "查询模式，值可为（1-15，除6、7、14、15），按时间段查询为1，按地块查询为2，按植株查询为4，按操作人查询为8，多条件查询则为条件对应值之和")
    private int mode;
}
