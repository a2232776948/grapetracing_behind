package cn.edu.scau.controller;

import cn.edu.scau.model.Logistic;
import cn.edu.scau.model.SearchLogisticForm;
import cn.edu.scau.service.LogisticService;
import cn.edu.scau.util.ExpUtil;
import cn.edu.scau.util.Response;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logistic")
@Api(tags = "物流")
public class LogisticController {

    @Autowired
    private LogisticService logisticService;

    @ApiOperation("获取所有物流信息")
    @GetMapping("getAllLogistic")
    public Response<List<Logistic>> getAllLogistic(){
        List<Logistic> allLogistic = logisticService.getAllLogistic();
        return Response.ok("获取成功",allLogistic);
    }

    @ApiOperation("录入单号")
    @PostMapping("addLogistic")
    public Response addLogistic(@RequestBody Logistic logistic){
        int i = logisticService.addLogistic(logistic);
        if(i == logistic.getGoodsIds().length){
            return Response.ok("录入成功");
        }else{
            return Response.ok("录入失败");
        }
    }

    @ApiOperation("批量删除单号")
    @PostMapping("delete")
    public Response delete(@RequestBody long[] ids){
        int count = logisticService.delete(ids);
        if(count == ids.length){
            return Response.ok("删除成功");
        }else{
            return Response.ok("删除失败");
        }

    }

    @ApiOperation("搜索物流信息")
    @PostMapping("search")
    public Response search(@RequestBody SearchLogisticForm form){
        List<Logistic> search = logisticService.search(form);
        return Response.ok("查找成功",search);
    }

    @ApiOperation("获取快递状态")
    @GetMapping("getAllStatus")
    public Response getAllStatus(){
        List<String> allStatus = logisticService.getAllStatus();
        return Response.ok("获取成功",allStatus);
    }

    @ApiOperation(value = "获取快递信息",notes = "返回值信息可以用postman，查个单号看一下格式(可以用YT4448863027564)")
    @GetMapping("getExpInfo")
    @ApiModelProperty(name = "expNo",value = "快递单号")
    public Response<JSONObject> getExpInfo(String expNo){
        String expInfo = logisticService.getExp(expNo);
        JSONObject jsonObject = JSONObject.parseObject(expInfo);
        return Response.ok("获取成功",jsonObject);
    }

}
