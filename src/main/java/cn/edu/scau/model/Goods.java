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
public class Goods {
    private long id;
    private long treeId;
    private Date date;
    @ApiModelProperty(value = "添加的数量")
    private int count=1;
}
