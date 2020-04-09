package cn.edu.scau.controller;

import cn.edu.scau.model.Quality;
import cn.edu.scau.service.IQualityService;
import cn.edu.scau.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "质检信息")
@RestController
@RequestMapping("/quality")
public class QualityController {
    @Autowired
    IQualityService qualityService;

    @ApiOperation("添加质检信息")
    @PostMapping("/addOneInfo")
    public Response addOneInfo(@RequestBody Quality quality) {
        if (qualityService.addOneInfo(quality) == 1) {
            return Response.ok("添加成功");
        } else
            return Response.error("添加失败");
    }

    @ApiOperation("获取所有质检信息")
    @GetMapping("/getAllQuality")
    public Response getAllQuality(){
        return Response.ok("获取成功", qualityService.getAllQuality());
    }

    @ApiOperation("删除一条或多条质检信息")
    @PostMapping("/remove")
    public Response remove(@RequestBody List<Long> ids){
        if (qualityService.remove(ids) == ids.size())
            return Response.ok("删除成功");
        else
            return Response.error("删除失败");
    }

    @ApiOperation("修改一条质检信息")
    @PostMapping("/updateOne")
    public Response updateOne(@RequestBody Quality quality){
        if (qualityService.updateOne(quality) == 1)
            return Response.ok("修改成功");
        else
            return Response.error("修改失败");
    }

    @ApiOperation("根据id获取质检信息")
    @GetMapping("/getQualityById")
    public Response getQualityById(long id){
        return Response.ok("查找成功", qualityService.getQualityById(id));
    }
}
