package cn.edu.scau.controller;

import cn.edu.scau.dao.GoodsDao;
import cn.edu.scau.model.*;
import cn.edu.scau.service.IGoodsService;
import cn.edu.scau.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public Response<List<Goods>> getAllGoods(){
        return Response.ok("获取成功",goodsService.getAllGoods());
    }

    @ApiOperation(value = "为某棵植株添加商品",notes = "id和trackingNumber不用填")
    @RequestMapping(value = "addGoodsForTree",method = RequestMethod.POST)
    public Response addGoodsForTree(@RequestBody Goods goods){
        goodsService.addGoodsForTree(goods);
        return Response.ok("添加成功");
    }

    @ApiOperation("为某个地块下的所有植株添加商品")
    @RequestMapping(value = "addGoodsForArea",method = RequestMethod.POST)
    public Response addGoodsForArea(@RequestBody AddGoodsForm addGoodsForm){
        goodsService.addGoodsForArea(addGoodsForm);
        return Response.ok("添加成功");
    }

    @ApiOperation("按条件搜索葡萄商品")
    @RequestMapping(value = "findGoods",method = RequestMethod.POST)
    public Response<List<GoodsForm>> findGoods(@RequestBody SearchGoodsForm searchForm){
        List<GoodsForm> goodsForm = goodsService.findGoodsForm(searchForm);
        return Response.ok("搜索成功",goodsForm);
    }

    @ApiOperation("批量删除商品,尚未实现")
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

    @ApiOperation("根据年份获得葡萄商品的数量")
    @RequestMapping(value = "getYearGoods",method = RequestMethod.GET)
    public Response<YearGoodsCount> getYearGoods(long year){
        List<YearGoodsCount> yearGoodsCount = goodsService.getYearGoodsCount(year);
        return Response.ok("获取成功",yearGoodsCount);
    }

    @ApiOperation("根据葡萄商品Id获取葡萄商品信息")
    @GetMapping("getGoodsById")
    public Response<Long> getGoodsById(long id){
        Goods goodsById = goodsService.getGoodsById(id);
        return Response.ok("获取成功",goodsById);
    }

    @ApiOperation("根据葡萄商品溯源码获取葡萄商品ID")
    @ApiImplicitParam(name = "id",value = "葡萄商品溯源码")
    @GetMapping("getGoodsByQrId")
    public Response<Long> getGoodsByQrId(String id){
        long goodsByQrId = 0;
        try{
            goodsByQrId = goodsService.getGoodsByQrId(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Response.error("商品不存在");
        }
        return Response.ok("获取成功",goodsByQrId);
    }

    @ApiOperation("获取葡萄商品展示表格的数据")
    @GetMapping("getGoodsForm")
    public Response<GoodsForm> getGoodsForm(){
        List<GoodsForm> goodsForm = goodsService.getGoodsForm();
        return Response.ok("获取成功",goodsForm);
    }

    @ApiOperation("导出某个商品的二维码")
    @RequestMapping(value = "getGoodsQRcode",method = RequestMethod.GET)
    public Response<String> getGoodsQRcode(long id, HttpServletResponse response){
        try {
            goodsService.getGoodsQRcode( id, response);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
        return Response.ok("导出成功");
    }

    @ApiOperation("获取葡萄商品种类")
    @GetMapping("getCategory")
    public Response<List<String>> getCategory(){
        List<String> category = goodsService.getCategory();
        return Response.ok("获取成功",category);
    }

    @ApiOperation("批量产生葡萄商品二维码")
    @ApiImplicitParam(name="ids" , value="商品编号")
    @RequestMapping(value = "addGoodsQRcodes",method = RequestMethod.POST)
    public Response<String> addGoodsQRcodes(@RequestBody long[] ids){
        String goodsQRCodesUrl = "";
        try {
            goodsQRCodesUrl = goodsService.addGoodsQRcodes(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error("生成失败"+e.getMessage());
        }
        return Response.ok("生成成功",goodsQRCodesUrl);
    }

    @ApiOperation("批量导出葡萄商品二维码")
    @RequestMapping(value = "getGoodsQRcodes",method = RequestMethod.GET)
    public Response getGoodsQRcodes(String url,HttpServletResponse response) {
        try {
            goodsService.getGoodsQRCodes(url,response);
        }catch (Exception e){
            e.printStackTrace();
            return Response.error("导出失败"+e.getMessage());
        }
        return Response.ok("导出成功");
    }


}
