package cn.edu.scau.controller;

import cn.edu.scau.model.*;
import cn.edu.scau.service.IFarmOPService;
import cn.edu.scau.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "农事操作")
@RestController
@RequestMapping("/farmop")
public class FarmOPController {

    @Autowired
    private IFarmOPService farmOPServiceImpl;


    @ApiOperation("获取所有农事操作")
    @GetMapping("/getAllOps")
    public Response<List<FarmOP>> getAllOps(){
        return Response.ok("查找成功", farmOPServiceImpl.getAllOPs());
    }

    @ApiOperation("按条件搜索农事操作")
    @PostMapping("/findFarmOPs")
    public Response<List<FarmOP>> findFarmOPs(@RequestBody SearchFarmOPForm form){
        System.out.println(form);
        return Response.ok("查找成功", farmOPServiceImpl.findFarmOPs(form));
    }

    @ApiOperation("获取农事操作种类")
    @GetMapping("/getAllFarmCate")
    public Response<List<FarmOPCategory>> getAllFarmCate(){
        return Response.ok("查找成功", farmOPServiceImpl.getAllFarmCate());
    }

    @ApiOperation("获取所有地块的农事次数")
    @RequestMapping(value = "getAreaFarmopCount",method = RequestMethod.GET)
    public Response<List<AreaFarmOPCount>> getAreaFarmopCount(){
        List<AreaFarmOPCount> areaFarmOPCounts = farmOPServiceImpl.selectAreaFarmOPCount();
        return Response.ok("获取成功",areaFarmOPCounts);
    }

    @ApiOperation("按年份获取农事操作次数")
    @ApiImplicitParam(name = "year",example = "2019")
    @RequestMapping(value = "getYearFarmopCount",method = RequestMethod.GET)
    public Response<List<YearFarmopCount>> getYearFarmopCount(long year){
        List<YearFarmopCount> yearFarmopCounts = farmOPServiceImpl.selectYearFarmopCount(year);
        return Response.ok("获取成功",yearFarmopCounts);
    }


    @ApiOperation("通过地块添加农事操作")
    @PostMapping("/insertOneFarmopForArea")
    public Response addOneFarmopForArea(@RequestBody FarmOP farmOP){
       if(farmOPServiceImpl.addOneFarmopForArea(farmOP)){
           return Response.ok("插入成功");
       }else
           return Response.error("插入失败");
    }

    @ApiOperation("通过地块添加农事操作")
    @PostMapping("/insertOneFarmopForTree")
    public Response addOneFarmopForTree(@RequestBody FarmOP farmOP){
        if(farmOPServiceImpl.addOneFarmopForTree(farmOP) == 1){
            return Response.ok("插入成功");
        }else
            return Response.error("插入失败");
    }

    @ApiOperation("根据id修改农事")
    @PostMapping("/updateOneFarmop")
    public Response editOneFarmop(@RequestBody FarmOP farmOP){
        if(farmOPServiceImpl.editOneFarmop(farmOP) == 1){
            return Response.ok("修改成功");
        }else
            return Response.error("修改失败");
    }

    @ApiOperation("删除一项或多项农事")
    @PostMapping("/delete")
    public Response delete(@RequestBody List<Long> ids){
        if(farmOPServiceImpl.delete(ids) == ids.size()){
            return Response.ok("删除成功");
        }else
            return Response.error("删除失败");
    }

}
