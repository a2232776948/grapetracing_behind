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
@ApiModel("搜索物流信息")
public class SearchLogisticForm {
    private long goodsId;
    private String expressNumber;
    private String status;
    private Date startDate;
    private Date endDate;
    @ApiModelProperty(value = "1日期，2产品编号，4快递单号，8状态，需要哪个条件把对应的数字加上即可，0无条件")
    int mode=0;
}
