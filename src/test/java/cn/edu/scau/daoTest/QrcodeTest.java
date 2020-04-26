package cn.edu.scau.daoTest;

import cn.edu.scau.service.IAreaService;
import cn.edu.scau.util.FastDFSClientUtil;
import cn.edu.scau.util.image.ImageUtil;
import cn.edu.scau.util.qrcode.QRCodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import static cn.edu.scau.util.fileutil.ToZip.zipFile;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QrcodeTest {

    @Autowired
    private FastDFSClientUtil dfsClient;

    @Autowired
    private IAreaService areaService;

    @Test
    public void createQrcode() throws Exception {
        // 存放在二维码中的内容
        String text = "我是小铭";
        // 嵌入二维码的图片路径
        String imgPath = "D:/珠海项目/葡萄溯源/grape_trace20200408/test1/262.jpg";
        // 生成的二维码的路径及名称
        String destPath = "D:/珠海项目/葡萄溯源/grape_trace20200408/test1/262qrcode.jpg";
        //生成二维码
        QRCodeUtil.encode(text, imgPath, destPath, true);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);
    }

    @Test
    public void createQrcode2() throws Exception {
        String test = "qwer";
        //String c = System.getProperty("user.dir");
        //String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String path = ResourceUtils.getURL("classpath:").getPath();

        System.out.println("path="+path);
        String imagePath=path+ "static/area.jpg";
        MultipartFile multipartFile = QRCodeUtil.encodeToMultipartFile(test, imagePath, true);
        File toFile = null;
        InputStream ins = null;
        ins = multipartFile.getInputStream();
        toFile = new File(multipartFile.getOriginalFilename()+'.'+multipartFile.getContentType());
        inputStreamToFile(ins, toFile);
        ins.close();
    }

    @Test
    public void createQrcode3() throws Exception {
        MultipartFile multipartFile = null;
        //String basePath = ResourceUtils.getURL("classpath:").getPath();
        String fullPath = "C:/Users/a2232776948/Pictures/262.jpg";
        String info = "treeId=2" ;
        multipartFile = QRCodeUtil.encodeToMultipartFile(info, fullPath, true);

        String fileUrl = dfsClient.uploadFile(multipartFile);
        System.out.println("fileUrl="+fileUrl);
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void zipFileTest() throws IOException {
        zipFile("D:\\zhuhai_project\\grape_tracing\\grape_trace20200408\\grape_tracingback-master\\sd",
                "D:\\zhuhai_project\\grape_tracing\\grape_trace20200408\\grape_tracingback-master\\sd.zip");
    }

    @Test
    public void getAreaQRCode() throws Exception {
        long[] ids= {1,2,3};
        //areaService.getAreaQRCode(ids);
    }

    @Test
    public void imagePaint() throws IOException {
        String bigPath = "C:/Users/a2232776948/Pictures/262.jpg";
        String smallPath = "C:/Users/a2232776948/Pictures/tree.jpg";
        String x = "20";
        String y = "20";
        ImageUtil.mergeImage(bigPath,smallPath,x,y);

    }


}
