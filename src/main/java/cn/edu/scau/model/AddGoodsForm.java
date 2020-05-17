package cn.edu.scau.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class AddGoodsForm {
    private Integer areaId;
    private String category;
    private Date date;
    private String desc;
    @ApiModelProperty(notes = "为每棵植株添加的商品的数量")
    private Integer count;
}
