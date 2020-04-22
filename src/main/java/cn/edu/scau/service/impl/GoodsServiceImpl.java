package cn.edu.scau.service.impl;

import cn.edu.scau.dao.GoodsDao;
import cn.edu.scau.dao.TreeDao;
import cn.edu.scau.model.*;
import cn.edu.scau.service.IGoodsService;
import cn.edu.scau.util.FastDFSClientUtil;
import cn.edu.scau.util.qrcode.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private TreeDao treeDao;

    @Autowired
    private FastDFSClientUtil dfsClient;

    @Value("${fdfs.reqBaseUrl}")
    private String reqBaseUrl;  //nginx的ip+端口号

    @Value("${goodsQRIdBase}")
    private String goodsQRIdBase;

    @Override
    public List<GoodsQRcode> getAllGoods() {
        return goodsDao.getAllGoods();
    }

    @Override
    public boolean addGoodsForTree(Goods goods) {
        goodsDao.addGoodsForTree(goods);
        return true;
    }

    @Override
    public boolean addGoodsForArea(AddGoodsForm addGoodsForm) {
        List<Tree> trees = treeDao.selectTreeByAreaId(addGoodsForm.getAreaId());
        for(Tree tree : trees){
            Goods goods = new Goods();
            goods.setTreeId(tree.getId());
            goods.setDate(addGoodsForm.getDate());
            goods.setCount(addGoodsForm.getCount());
            goodsDao.addGoodsForTree(goods);
        }
        return true;
    }

    @Override
    public int deleteGoods(List<Integer> ids) {
        return 0;
    }

    @Override
    public String getGoodsQRcode(Integer id) throws Exception {
        MultipartFile multipartFile = null;
        String basePath = ResourceUtils.getURL("classpath:").getPath();
        String fullPath = basePath + "static/grape.jpg";
        String info = goodsQRIdBase+String.valueOf(id);
        multipartFile = QRCodeUtil.encodeToMultipartFile(info, fullPath, true);
        String fileUrl = dfsClient.uploadFile(multipartFile);

        return fileUrl;
    }
}
