package cn.edu.scau.service.impl;

import cn.edu.scau.dao.AreaDao;
import cn.edu.scau.dao.TreeDao;
import cn.edu.scau.model.Area;
import cn.edu.scau.service.IAreaService;
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
import java.util.Date;
import java.util.List;
import java.util.Timer;

import static cn.edu.scau.util.fileutil.ToZip.zipFile;

@Service
public class AreaServiceImpl implements IAreaService {

    private String areaQrPath = getbaseURL()+"static/upload/qrimage/area/";

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private TreeDao treeDao;

    @Autowired
    private FastDFSClientUtil dfsClient;

    public AreaServiceImpl() throws FileNotFoundException {
    }

    private String getbaseURL() throws FileNotFoundException {
        return ResourceUtils.getURL("classpath:").getPath();
    }

    @Override
    public List<Area> getAllArea() {
        List<Area> areas = areaDao.selectAll();
        for (Area area : areas) {
            area.treeCount = treeDao.countTreeByAreaId(area.id);
        }
        return areas;
    }

    @Override
    public void addArea(Area area) {
        areaDao.insertOne(area);
    }

    @Override
    public void removeAreas(long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            treeDao.deleteByAreaId(ids[i]);
            areaDao.deleteOneById(ids[i]);
        }
    }

    @Override
    public void changeAreaInfo(Area area) {
        areaDao.updateOne(area);
    }

//    @Override
//    public String getAreaQRCode(Integer id) throws Exception {
//        MultipartFile multipartFile = null;
//        String basePath = ResourceUtils.getURL("classpath:").getPath();
//        String fullPath = basePath + "static/area.jpg";
//        String info = "areaId=" + String.valueOf(id);
//        multipartFile = QRCodeUtil.encodeToMultipartFile(info, fullPath, true);
//
//        String fileUrl = dfsClient.uploadFile(multipartFile);
//        return fileUrl;
//    }

    @Override
    public String getAreaQRCodes(long[] ids, HttpServletResponse response) throws Exception {
        String basePath = ResourceUtils.getURL("classpath:").getPath();
        String fullPath = basePath + "static/area.jpg";
        Date date = new Date();
        String filePath = areaQrPath+ String.valueOf(date.getTime());
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
        for(long id : ids){
            String info = "areaId=" + String.valueOf(id);
            String target = filePath+'/'+info+".jpg";
            QRCodeUtil.encode(info,fullPath,target);
        }
        zipFile(filePath,filePath+".zip");
        //FileUpload.fileUpload(response,basePath+"static/upload/qrimage.zip");
        //file.deleteOnExit();
        //file = new File(basePath+"static/upload/qrimage.zip");
        //file.deleteOnExit();
        return filePath;
    }

    @Override
    public boolean getAreaQRCode(long id,HttpServletResponse response) throws Exception {
        String basePath = ResourceUtils.getURL("classpath:").getPath();
        String fullPath = basePath + "static/area.jpg";
        String info = "areaId=" + String.valueOf(id);
        String target = areaQrPath+info+".jpg";
        QRCodeUtil.encode(info,fullPath,target);
        File file = new File(target);
        FileUpload.fileUpload(response,target);
        file.deleteOnExit();

        return true;
    }
}
