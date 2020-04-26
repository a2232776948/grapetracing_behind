package cn.edu.scau.service;

import cn.edu.scau.model.Area;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface IAreaService {

    List<Area> getAllArea();

    void addArea(Area area);

    void removeAreas(long[] ids);

    void changeAreaInfo(Area area);

    boolean getAreaQRCode(long id,HttpServletResponse response) throws Exception;

    String getAreaQRCodes(long[] ids, HttpServletResponse response) throws Exception;

    //boolean getAreaQRCodes(long[] ids) throws Exception;
}
