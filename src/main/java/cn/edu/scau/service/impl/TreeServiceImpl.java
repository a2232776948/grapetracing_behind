package cn.edu.scau.service.impl;

import cn.edu.scau.dao.AreaDao;
import cn.edu.scau.dao.TreeDao;
import cn.edu.scau.model.Company;
import cn.edu.scau.model.SearchTreeForm;
import cn.edu.scau.model.Tree;
import cn.edu.scau.model.TreeCategory;
import cn.edu.scau.service.ITreeService;
import cn.edu.scau.util.FastDFSClientUtil;
import cn.edu.scau.util.fileutil.FileUpload;
import cn.edu.scau.util.qrcode.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static cn.edu.scau.util.fileutil.ToZip.zipFile;

@Service
public class TreeServiceImpl implements ITreeService {
    @Autowired
    private Company company;
    @Autowired
    private TreeDao treeDao;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private FastDFSClientUtil dfsClient;

    public TreeServiceImpl() throws FileNotFoundException {
    }

    private String getBasePath(){
        String areaQrPath = "";
        try{
            areaQrPath = QRCodeUtil.getbaseURL()+"/static/upload/qrimage/tree/";
        }catch (Exception e){
            System.out.println("QRCodeUtil.getbaseURL()在测试环境无效");
        }
        return areaQrPath;
    }

    @Override
    public List<Tree> getAllTrees() {
        return treeDao.selectAll();
    }

    @Override
    public List<TreeCategory> getAllCategory() {
        List<TreeCategory> categories = treeDao.selectAllCategory();
        for (TreeCategory category : categories) {
            category.count = treeDao.selectCountByCategory(category.name);
            category.alive = treeDao.selectCountByStatus("存活");
            category.dead = treeDao.selectCountByStatus("死亡");
        }
        return categories;
    }

    @Override
    public void addTrees(Tree tree) {
        /*for (int i = 0; i < tree.count; i++) {
            treeDao.insertOne(tree);
        }*/
        treeDao.insertMany(tree);
    }

    @Override
    public void removeTrees(long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            treeDao.deleteById(ids[i]);
        }
    }

    @Override
    public void editTree(Tree tree) {
        treeDao.updateOneById(tree);
    }

    @Override
    public List<Tree> findTrees(SearchTreeForm form) {
        List<Tree> trees = null;
        switch (form.getMode()) {
            case 1:
                trees = treeDao.selectTreeByPeriod(form.getStart_date(), form.getEnd_date());
                break;
            case 2:
                trees = treeDao.selectTreeByAreaId(form.getArea_id());
                break;
            case 3:
                trees = treeDao.selectTreeByAreaIdAndPeriod(form.getArea_id(), form.getStart_date(), form.getEnd_date());
                break;
            case 4:
                Tree tree = treeDao.selectTreeById(form.getId());
                trees = new ArrayList<>();
                if (tree != null)
                    trees.add(tree);
                break;
            case 8:
                trees = treeDao.selectTreeByStatus(form.getStatus());
                break;
            case 9:
                trees = treeDao.selectTreeByStatusAndPeriod(form.getStatus(), form.getStart_date(), form.getEnd_date());
                break;
            case 10:
                trees = treeDao.selectTreeByAreaIdAndStatus(form.getArea_id(), form.getStatus());
                break;
            case 11:
                trees = treeDao.selectTreeByAreaIdAndPeriodAndStatus(form.getArea_id(), form.getStart_date(), form.getEnd_date(), form.getStatus());
                break;
            default:
                break;
        }
        if (trees == null) {
            trees = new ArrayList<>();
        }
        return trees;
    }

    @Override
    public boolean getTreeQRCode(long id, HttpServletResponse response) throws Exception {
        String note = company.getCompanyName();
        InputStream image = this.getClass().getResourceAsStream("/static/tree.jpg");
        String info = "treeId=" + String.valueOf(id);
        String target = getBasePath() + "static/upload/qrimage/"+info+".jpg";
        QRCodeUtil.encode(info,image,target,note);
        File file = new File(target);
        FileUpload.fileUpload(response,target);
        file.deleteOnExit();

        return true;
    }

    @Override
    public String addTreeQRCodes(long[] ids) throws Exception {
        String note = company.getCompanyName();
        Date date = new Date();
        String filePath = getBasePath()+ String.valueOf(date.getTime());
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
        for(long id : ids){
            InputStream image = this.getClass().getResourceAsStream("/static/tree.jpg");
            String info = "treeId=" + String.valueOf(id);
            String target = filePath+'/'+info+".jpg";
            QRCodeUtil.encode(info,image,target,note);
        }
        zipFile(filePath,filePath+".zip");
        return filePath+".zip";
    }

    @Override
    public boolean getTreeQRCodes(String url, HttpServletResponse response) {
        FileUpload.fileUpload(response,url);
        File file = new File(url);
        file.deleteOnExit();
        return true;
    }
}
