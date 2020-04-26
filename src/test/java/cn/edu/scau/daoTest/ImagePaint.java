package cn.edu.scau.daoTest;

import cn.edu.scau.util.image.ImageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImagePaint {

    @Test
    public void imagePaint1_1() throws IOException {
        ImageUtil.imagePaint2();
    }

    @Test
    public void test1(){
        Date date = new Date();
        System.out.println(String.valueOf(date));
    }

}
