package cn.edu.scau.daoTest;


import cn.edu.scau.util.qrcodeutil.QRCodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilTest {
    @Test
    public void qrCodeTest() throws Exception {
        String text = "http://www.cnblogs.com/haha12";
        File file = QRCodeUtil.encode(text, "C:\\Users\\a2232776948\\Pictures\\262.jpg", "d:/data", true);
        System.out.println("生成二维码名称："+file.getName());

        String res = QRCodeUtil.decode(file);

        System.out.println("解析的二维码内容："+res);
    }


}
