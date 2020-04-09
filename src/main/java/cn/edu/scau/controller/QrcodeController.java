package cn.edu.scau.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Api("二维码")
@RequestMapping("/qrcode")
public class QrcodeController {

    @ApiOperation("获取一颗树的二维码")
    @GetMapping("/getTreeQrcode")
    public MultipartFile getTreeQrcode(int id){
        File file = new File("C:\\Users\\a2232776948\\Pictures\\262.jpg");
        //FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
        return null;
    }
}
