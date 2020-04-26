package cn.edu.scau.controller;

import cn.edu.scau.util.Response;
import cn.edu.scau.model.Area;
import cn.edu.scau.service.IAreaService;
import cn.edu.scau.util.qrcode.QRCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "地块")
@RestController
@RequestMapping("/area")
public class AreaController {
    @Autowired
    private IAreaService IAreaService;

    @ApiOperation("获取所有地块")
    @RequestMapping(value = "getAllArea", method = RequestMethod.GET)
    public Response<List<Area>> getAllArea() {
        return Response.ok("获取成功", IAreaService.getAllArea()) ;
    }

    @ApiOperation("添加地块")
    @RequestMapping(value = "addArea", method = RequestMethod.POST)
    public Response addArea(@RequestBody Area area){
        IAreaService.addArea(area);
        return Response.ok("添加成功");
    }

    @ApiOperation("批量删除地块")
    @RequestMapping(value = "deleteAreas", method = RequestMethod.POST)
    public Response deleteAreas(@RequestBody long[] ids){
        IAreaService.removeAreas(ids);
        return Response.ok("删除成功");
    }

    @ApiOperation("修改地块信息")
    @RequestMapping(value = "changeAreaInfo", method = RequestMethod.POST)
    public Response changeAreaInfo(@RequestBody Area area){
        IAreaService.changeAreaInfo(area);
        return Response.ok("修改成功");
    }

    @ApiOperation("批量导出地块二维码")
    @RequestMapping(value = "getAreaQRcodes",method = RequestMethod.GET)
    public Response getAreaQRcodes( long[] ids, HttpServletResponse response) {
        try {
            IAreaService.getAreaQRCodes(ids,response);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
        return Response.ok("导出成功");
    }

    @ApiOperation("导出某个地块二维码")
    @RequestMapping(value = "getAreaQRcode",method = RequestMethod.GET)
    public Response getAreaQRcode(long id, HttpServletResponse response) {
        try {
            IAreaService.getAreaQRCode(id,response);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
        return Response.ok("导出成功");
    }

    @ApiOperation("批量产生地块二维码")
    @ApiImplicitParam(name="ids" , value="地块编号")
    @RequestMapping(value = "addAreaQRcodes",method = RequestMethod.POST)
    public Response<String> addAreaQRcodes(long[] ids, HttpServletResponse response){
        try {
            IAreaService.getAreaQRCodes(ids, response);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(e.getMessage());
        }
        return Response.ok("导出成功");
    }

    @ApiOperation("批量导出地块二维码")
    @RequestMapping(value = "getAreaQRcodes",method = RequestMethod.POST)
    public Response getAreaQRcodes(long[] ids){
        return Response.ok("");
    }


}
