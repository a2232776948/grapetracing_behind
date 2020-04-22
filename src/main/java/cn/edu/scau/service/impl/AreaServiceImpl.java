package cn.edu.scau.service.impl;

import cn.edu.scau.dao.AreaDao;
import cn.edu.scau.dao.TreeDao;
import cn.edu.scau.model.Area;
import cn.edu.scau.service.IAreaService;
import cn.edu.scau.util.FastDFSClientUtil;
import cn.edu.scau.util.qrcode.QRCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AreaServiceImpl implements IAreaService {

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private TreeDao treeDao;

    @Autowired
    private FastDFSClientUtil dfsClient;

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

    @Override
    public String getAreaQRCode(Integer id) throws Exception {
        MultipartFile multipartFile = null;
        String basePath = ResourceUtils.getURL("classpath:").getPath();
        String fullPath = basePath + "static/area.jpg";
        String info = "areaId=" + String.valueOf(id);
        multipartFile = QRCodeUtil.encodeToMultipartFile(info, fullPath, true);

        String fileUrl = dfsClient.uploadFile(multipartFile);
        return fileUrl;
    }
}
