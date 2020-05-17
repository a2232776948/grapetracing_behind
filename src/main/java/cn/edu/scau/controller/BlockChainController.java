package cn.edu.scau.controller;

import cn.edu.scau.blockchain.BlockchainService;
import cn.edu.scau.model.BlockChainNode;
import cn.edu.scau.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "区块链管理")
@RequestMapping("/blockchain")
public class BlockChainController {

    @Autowired
    BlockchainService blockchainService;

    @ApiOperation("通过溯源码查询，暂时为树的id")
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Response find(long id) {
        List<BlockChainNode> nodes = blockchainService.pop(id);
        return Response.ok("查询成功", nodes);
    }

    @ApiOperation("获取区块数量")
    @RequestMapping(value = "blockNumber", method = RequestMethod.GET)
    public String blockNumber() {
        return blockchainService.getBlockNumber().toString();
    }

    @ApiOperation("停止挖矿")
    @RequestMapping(value = "/stopMiner", method = RequestMethod.POST)
    public Response stopMiner() {
        boolean hasDone = blockchainService.stopMiner();
        if (hasDone)
            return Response.ok("停止成功");
        else
            return Response.error("停止失败");
    }

    @ApiOperation("开启挖矿")
    @RequestMapping(value = "/startMiner", method = RequestMethod.POST)
    public Response startMiner() {
        boolean hasDone = blockchainService.startMiner();
        if (hasDone)
            return Response.ok("开启成功");
        else
            return Response.error("开启失败");
    }

    @ApiOperation("查看挖矿状态")
    @RequestMapping(value = "/mining", method = RequestMethod.GET)
    public boolean mining() {
        return blockchainService.mining();
    }
}
