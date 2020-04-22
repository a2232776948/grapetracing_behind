package cn.edu.scau.controller;

import cn.edu.scau.model.AddGoodsForm;
import cn.edu.scau.model.Goods;
import cn.edu.scau.model.GoodsQRcode;
import cn.edu.scau.service.IGoodsService;
import cn.edu.scau.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Api(tags = "货品")
@RestController
@RequestMapping("/goods")
public class GoodsController {
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

    @ApiOperation("批量删除商品，尚未实现")
    @RequestMapping(value = "deleteGoods",method = RequestMethod.POST)
    public Response deleteGoods(@RequestBody List<Integer> ids){
        return Response.ok("删除成功");
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
