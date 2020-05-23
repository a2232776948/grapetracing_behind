package cn.edu.scau.service.impl;

import cn.edu.scau.dao.AreaDao;
import cn.edu.scau.dao.TreeDao;
import cn.edu.scau.model.Area;
import cn.edu.scau.model.Company;
import cn.edu.scau.service.IAreaService;
import cn.edu.scau.util.FastDFSClientUtil;
import cn.edu.scau.util.fileutil.FileUpload;
import cn.edu.scau.util.qrcode.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import static cn.edu.scau.util.fileutil.ToZip.zipFile;

@Service
public class AreaServiceImpl implements IAreaService {
    @Autowired
    private Company company;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private TreeDao treeDao;

    @Autowired
    private FastDFSClientUtil dfsClient;

    private String getBasePath(){
        String areaQrPath = "";
        try{
            areaQrPath = QRCodeUtil.getbaseURL()+"/static/upload/qrimage/area/";
        }catch (Exception e){
            System.out.println("QRCodeUtil.getbaseURL()在测试环境无效");
        }
        return areaQrPath;
    }

//    private String getbaseURL() throws FileNotFoundException {
//        return ResourceUtils.getURL("classpath:").getPath();
//    }

//    private String getbaseURL() throws FileNotFoundException {
//        return System.getProperty("user.dir");
//    }
//

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
    public String addAreaQRCodes(long[] ids) throws Exception {
        String areaQrPath = getBasePath();
        String note = company.getCompanyName();
        Date date = new Date();
        String filePath = areaQrPath+ String.valueOf(date.getTime());
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
        for(long id : ids){
            InputStream image = this.getClass().getResourceAsStream("/static/area.jpg");
            String info = "areaId=" + String.valueOf(id);
            String target = filePath+'/'+info+".jpg";
            QRCodeUtil.encode(info,image,target,note);
        }
        zipFile(filePath,filePath+".zip");
        //FileUpload.fileUpload(response,basePath+"static/upload/qrimage.zip");
        //file.deleteOnExit();
        //file = new File(basePath+"static/upload/qrimage.zip");
        //file.deleteOnExit();
        return filePath+".zip";
    }

    @Override
    public boolean getAreaQRCodes(String url, HttpServletResponse response) throws Exception {
        FileUpload.fileUpload(response,url);
        File file = new File(url);
        file.deleteOnExit();
        return true;
    }

//    @Override
//    public boolean getAreaQRCode(long id,HttpServletResponse response) throws Exception {
//        String note = company.getCompanyName();
//        String basePath = ResourceUtils.getURL("classpath:").getPath();
//        String fullPath = basePath + "static/area.jpg";
//        String info = "areaId=" + String.valueOf(id);
//        String target = areaQrPath+info+".jpg";
//        QRCodeUtil.encode(info,fullPath,target,note);
//        File file = new File(target);
//        FileUpload.fileUpload(response,target);
//        file.deleteOnExit();
//        return true;
//    }@Override
//    public boolean getAreaQRCode(long id,HttpServletResponse response) throws Exception {
//        String note = company.getCompanyName();
//        String basePath = ResourceUtils.getURL("classpath:").getPath();
//        String smallImagePath = basePath+"static/area.jpg";
//        ResourceLoader resourceLoader = new DefaultResourceLoader();
//        InputStream inputStream = resourceLoader.getResource("classpath:/static/area.jpg").getInputStream();
//        FileOutputStream fos = new FileOutputStream(smallImagePath);
//        byte[] b = new byte[1024];
//        int length;
//        while((length=inputStream.read(b))>0){
//            fos.write(b,0,length);
//        }
//        inputStream.close();
//        fos.close();
//
//        String info = "areaId=" + String.valueOf(id);
//        String target = areaQrPath+info+".jpg";
//        QRCodeUtil.encode(info,smallImagePath,target,note);
//        File file = new File(target);
//        FileUpload.fileUpload(response,target);
//        file.deleteOnExit();
//        return true;
//    }
    public boolean getAreaQRCode(long id,HttpServletResponse response) throws Exception {
        String areaQrPath = getBasePath();
        String note = company.getCompanyName();

        InputStream image = this.getClass().getResourceAsStream("/static/area.jpg");
        String info = "areaId=" + String.valueOf(id);
        String target = areaQrPath+info+".jpg";
        QRCodeUtil.encode(info,image,target,note);
        FileUpload.fileUpload(response,target);
        return true;
    }

}
