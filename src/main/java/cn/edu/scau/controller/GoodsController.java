package cn.edu.scau.controller;

import cn.edu.scau.model.AddGoodsForm;
import cn.edu.scau.model.AreaGoodsCount;
import cn.edu.scau.model.Goods;
import cn.edu.scau.model.GoodsQRcode;
import cn.edu.scau.service.IGoodsService;
import cn.edu.scau.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "货品")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    //这里一个商品的id就是二维码的id
    @Autowired
    private IGoodsService goodsService;

    @ApiOperation("获取所有商品")
    @RequestMapping(value = "getAllGoods",method = RequestMethod.GET)
    public Response<List<GoodsQRcode>> getAllGoods(){
        return Response.ok("获取成功",goodsService.getAllGoods());
    }

    @ApiOperation("为某棵植株添加商品")
    @RequestMapping(value = "addGoodsForTree",method = RequestMethod.POST)
    public Response addGoodsForTree(@RequestBody Goods goods){
        goodsService.addGoodsForTree(goods);
        return Response.ok("添加成功");
    }

    @ApiOperation("为某个农田下的所有植株添加商品")
    @RequestMapping(value = "addGoodsForArea",method = RequestMethod.POST)
    public Response addGoodsForArea(@RequestBody AddGoodsForm addGoodsForm){
        goodsService.addGoodsForArea(addGoodsForm);
        return Response.ok("添加成功");
    }

    @ApiOperation("批量删除商品")
    @RequestMapping(value = "deleteGoods",method = RequestMethod.POST)
    public Response deleteGoods(@RequestBody List<Integer> ids){
        return Response.ok("删除成功");
    }

    @ApiOperation("获取所有地块的葡萄商品的数量")
    @RequestMapping(value = "getAreaGoodsCount",method = RequestMethod.GET)
    public Response<AreaGoodsCount> getAreaGoodsCount(){
        List<AreaGoodsCount> areaGoodsCount = goodsService.getAreaGoodsCount();
        return Response.ok("获取成功",areaGoodsCount);
    }

    @ApiOperation("根据普通商品Id获取葡萄商品")
    @GetMapping("getGoodsById")
    public Response<Long> getGoodsById(long id){
        Goods goodsById = goodsService.getGoodsById(id);
        return Response.ok("获取成功",goodsById);
    }

    @ApiOperation("获取商品的二维码")
    @RequestMapping(value = "getGoodsQRcode",method = RequestMethod.GET)
    public Response<String> getGoodsQRcode(Integer id){
        String goodsQRcode = "";
        try {
            goodsQRcode = goodsService.getGoodsQRcode(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
        return Response.ok("获取成功",goodsQRcode);
    }


}
