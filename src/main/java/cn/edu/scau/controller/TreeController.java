package cn.edu.scau.controller;

import cn.edu.scau.model.SearchTreeForm;
import cn.edu.scau.model.Tree;
import cn.edu.scau.service.ITreeService;
import cn.edu.scau.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "植株")
@RestController
@RequestMapping("/tree")
public class TreeController {
    @Autowired
    private ITreeService ITreeService;

    @ApiOperation("获取所有植株")
    @RequestMapping(value = "/getAllTrees", method = RequestMethod.GET)
    public Response getAllTrees() {
        return Response.ok("获取成功", ITreeService.getAllTrees());
    }

    @ApiOperation("获取植株种类")
    @RequestMapping(value = "/getAllCategory", method = RequestMethod.GET)
    public Response getAllCategory() {
        return Response.ok("获取成功", ITreeService.getAllCategory());
    }

    @ApiOperation("批量添加植株")
    @RequestMapping(value = "/addTrees", method = RequestMethod.POST)
    public Response addTrees(@RequestBody Tree tree) {
        ITreeService.addTrees(tree);
        return Response.ok("添加成功");
    }

    @ApiOperation("批量删除植株")
    @RequestMapping(value = "/removeTrees", method = RequestMethod.POST)
    public Response removeTrees(@RequestBody long[] ids) {
        ITreeService.removeTrees(ids);
        return Response.ok("删除成功");
    }

    @ApiOperation("编辑植株")
    @RequestMapping(value = "/editTree", method = RequestMethod.POST)
    public Response editTree(@RequestBody Tree tree) {
        ITreeService.editTree(tree);
        return Response.ok("编辑成功");
    }

    @ApiOperation("按条件搜索植株")
    @RequestMapping(value = "/findTrees", method = RequestMethod.POST)
    public Response findTrees(@RequestBody SearchTreeForm form) {
        System.out.println(form);
        List<Tree> trees = ITreeService.findTrees(form);
        return Response.ok(trees.size() > 0 ? "搜索成功" : "植株不存在", trees);
    }
}
