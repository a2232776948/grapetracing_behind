package cn.edu.scau.daoTest;

import cn.edu.scau.util.FastDFSClientUtil;
import cn.edu.scau.util.image.ImageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImagePaint {

    @Autowired
    FastDFSClientUtil fastDFSClientUtil;

    @Test
    public void imagePaint1_1() throws IOException {
        ImageUtil.imagePaint2();
    }

    @Test
    public void test1() throws IOException {
        File file = new File("C:\\Users\\a2232776948\\Desktop\\dog.jpg");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(),file.getName(),"jpg", inputStream);
        System.out.println(multipartFile.getContentType());
    }

    @Test
    public void uploadImage() throws IOException {
        File file = new File("C:\\Users\\a2232776948\\Desktop\\dog.jpg");
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(),file.getName(),"jpg", inputStream);

        String s = fastDFSClientUtil.uploadFile(multipartFile);
        System.out.println(s);
    }

}
