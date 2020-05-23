package cn.edu.scau.service.impl;

import cn.edu.scau.dao.GoodsDao;
import cn.edu.scau.dao.TreeDao;
import cn.edu.scau.model.*;
import cn.edu.scau.service.IGoodsService;
import cn.edu.scau.util.FastDFSClientUtil;
import cn.edu.scau.util.fileutil.FileUpload;
import cn.edu.scau.util.qrcode.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static cn.edu.scau.util.fileutil.ToZip.zipFile;

@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private Company company;

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

    public GoodsServiceImpl() throws FileNotFoundException {
    }

    private String getBasePath(){
        String areaQrPath = "";
        try{
            areaQrPath = QRCodeUtil.getbaseURL()+"/static/upload/qrimage/goods/";
        }catch (Exception e){
            System.out.println("QRCodeUtil.getbaseURL()在测试环境无效");
        }
        return areaQrPath;
    }

    @Override
    public List<Goods> getAllGoods() {
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
            goods.setCategory(addGoodsForm.getCategory());
            goods.setDesc(addGoodsForm.getDesc());
            goodsDao.addGoodsForTree(goods);
        }
        return true;
    }

    @Override
    public int deleteGoods(List<Integer> ids) {
        return 0;
    }


    @Override
    public List<AreaGoodsCount> getAreaGoodsCount() {
        List<AreaGoodsCount> areaGoodsCount = goodsDao.getAreaGoodsCount();
        return areaGoodsCount;
    }

    @Override
    public Goods getGoodsById(long id) {
        Goods goods = goodsDao.selectGoodsById(id);
        return goods;
    }

    @Override
    public List<YearGoodsCount> getYearGoodsCount(long year) {
        List<YearGoodsCount> yearGoodsCount = goodsDao.getYearGoodsCount(year);
        return yearGoodsCount;
    }

    @Override
    public List<GoodsForm> getGoodsForm() {
        List<GoodsForm> goodsForm = goodsDao.getGoodsForm();
        return goodsForm;
    }

    @Override
    public List<GoodsForm> findGoodsForm(SearchGoodsForm form) {
        List<GoodsForm> goodsForms = goodsDao.selectManyByCondition(form);
        return goodsForms;
    }


    @Override
    public long getGoodsByQrId(String qrId) {
        long goodsByQrId = goodsDao.getGoodsByQrId(qrId);
        return goodsByQrId;
    }

    @Override
    public boolean getGoodsQRcode(long id, HttpServletResponse response) throws Exception {
        String note = company.getCompanyName();
        String goodsQrPath = getBasePath();
        InputStream image = this.getClass().getResourceAsStream("/static/goods.jpg");
        Date date = new Date();
        String filePath = goodsQrPath+ String.valueOf(date.getTime());
        String info = "goods=" + String.valueOf(id);
        String target = filePath+'/'+info+".jpg";
        QRCodeUtil.encode(info,image,target,note);
        FileUpload.fileUpload(response,target);
        System.out.println("target:"+target);
        return true;
    }

    @Override
    public String addGoodsQRcodes(long[] ids) throws Exception {
        String note = company.getCompanyName();
        String goodsQrPath = getBasePath();
        Date date = new Date();
        String filePath = goodsQrPath+ String.valueOf(date.getTime());
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
        for(long id : ids){
            InputStream image = this.getClass().getResourceAsStream("/static/goods.jpg");
            String info = "goodsId=" + String.valueOf(id);
            String target = filePath+'/'+info+".jpg";
            QRCodeUtil.encode(info,image,target,note);
        }
        zipFile(filePath,filePath+".zip");
        return filePath+".zip";
    }

    @Override
    public boolean getGoodsQRCodes(String url, HttpServletResponse response) throws Exception {
        FileUpload.fileUpload(response,url);
        return true;
    }

    @Override
    public List<String> getCategory() {
        List<String> categorys = goodsDao.getCategory();
        return categorys;
    }

}
