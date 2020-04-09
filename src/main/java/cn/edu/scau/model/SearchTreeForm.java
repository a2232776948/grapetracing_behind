package cn.edu.scau.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("树搜索条件表")
public class SearchTreeForm {
    private long id;
    private long area_id;
    private Date start_date;
    private Date end_date;
    private String status;
    @ApiModelProperty(value = "查询模式，值可为（1、2、3、4、8、9、10、11），按时间段查询为1，按地块查询为2，按植株查询为4，按状态查询为8，多条件查询则为条件对应值之和")
    private int mode;
}
