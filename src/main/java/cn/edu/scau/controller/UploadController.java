package cn.edu.scau.controller;

import cn.edu.scau.service.IAreaService;
import cn.edu.scau.util.FastDFSClientUtil;
import cn.edu.scau.util.qrcode.QRCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "测试用")
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private FastDFSClientUtil dfsClient;

    @Autowired
    private IAreaService areaService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public String fdfsUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String fileUrl = "";
        try {
            fileUrl = dfsClient.uploadFile(file);
            System.out.println("fileUrl:"+fileUrl);
            request.setAttribute("msg", "成功上传文件，  '" + fileUrl + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileUrl;
    }


    /*
     * http://localhost/download?filePath=group1/M00/00/00/wKgIZVzZEF2ATP08ABC9j8AnNSs744.jpg
     */
    @RequestMapping("/download")
    public String download(String filePath , HttpServletRequest request , HttpServletResponse response) throws IOException {

        //    group1/M00/00/00/wKgIZVzZEF2ATP08ABC9j8AnNSs744.jpg
        String[] paths = filePath.split("/");
        String groupName = null ;
        for (String item : paths) {
            if (item.indexOf("group") != -1) {
                groupName = item;
                break ;
            }
        }
        String path = filePath.substring(filePath.indexOf(groupName) + groupName.length() + 1, filePath.length());
        InputStream input = dfsClient.download(groupName, path);

        //根据文件名获取 MIME 类型
        String fileName = paths[paths.length-1] ;
        System.out.println("fileName :" + fileName); // wKgIZVzZEF2ATP08ABC9j8AnNSs744.jpg
        String contentType = request.getServletContext().getMimeType(fileName);
        String contentDisposition = "attachment;filename=" + fileName;

        // 设置头
        response.setHeader("Content-Type",contentType);
        response.setHeader("Content-Disposition",contentDisposition);

        // 获取绑定了客户端的流
        ServletOutputStream output = response.getOutputStream();

        // 把输入流中的数据写入到输出流中
        IOUtils.copy(input,output);
        input.close();
        return "ok";
    }

    /**
     * http://localhost/deleteFile?filePath=group1/M00/00/00/wKgIZVzZaRiAZemtAARpYjHP9j4930.jpg
     * @param filePath  group1/M00/00/00/wKgIZVzZaRiAZemtAARpYjHP9j4930.jpg
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/deleteFile")
    public String delFile(String filePath , HttpServletRequest request , HttpServletResponse response)  {

        try {
            dfsClient.delFile(filePath);
        } catch(Exception e) {
            // 文件不存在报异常 ： com.github.tobato.fastdfs.exception.FdfsServerException: 错误码：2，错误信息：找不到节点或文件
            // e.printStackTrace();
        }
        request.setAttribute("msg", "成功删除，'" + filePath);

        return "ok";
    }

    //@GetMapping("/getAreaQRCode")
    public MultipartFile getAreaQRCode() throws Exception {
        String test = "qwer";
        String imagePath="D:/珠海项目/葡萄溯源/grape_trace20200408/test1/262.jpg";
        return QRCodeUtil.encodeToMultipartFile(test,imagePath,true);
    }

    @RequestMapping(value = "/migration",method = RequestMethod.GET)
    public void migration(HttpServletResponse response) throws Exception {
        String codedFileName = "EN";
        response.setHeader("Content-Disposition", "attachment;filename=" +             codedFileName + ".jpg");
        // 响应类型,编码
        response.setContentType("application/octet-stream;charset=UTF-8");
        // 形成输出流
        OutputStream osOut = response.getOutputStream();
        File xmlFileC = new
                File("D:\\zhuhai_project\\grape_tracing\\grape_trace20200408\\grape_tracingback-master\\tree.jpg");
        InputStream input = null;
        try {
            input = new FileInputStream(xmlFileC);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                osOut.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            osOut.close();
        }
    }

    @RequestMapping("/migration2")
    public static void buildExcelDocument(HttpServletResponse response){
        String filePath = "D:\\zhuhai_project\\grape_tracing\\grape_trace20200408\\grape_tracingback-master\\tree.jpg";
        ServletOutputStream out = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(new File(filePath));
            String[] dir = filePath.split("/");
            String fileName = dir[dir.length-1];
            String[] array = fileName.split("[.]");
            String fileType = array[array.length-1].toLowerCase();
            //设置文件ContentType类型
            if("jpg,jepg,gif,png".contains(fileType)){//图片类型
                response.setContentType("image/"+fileType);
            }else if("pdf".contains(fileType)){//pdf类型
                response.setContentType("application/pdf");
            }else{//自动判断下载文件类型
                response.setContentType("multipart/form-data");
            }
            //设置文件头：最后一个参数是设置下载文件名
            //response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
            out = response.getOutputStream();
            // 读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            System.out.println("responseFileStream error:FileNotFoundException" + e.toString());
        } catch (Exception e) {
            System.out.println("responseFileStream error:" + e.toString());
        } finally {
            try {
                out.close();
                in.close();
            } catch (NullPointerException e) {
                System.out.println("responseFileStream stream close() error:NullPointerException" + e.toString());
            } catch (Exception e) {
                System.out.println("responseFileStream stream close() error:" + e.toString());
            }
        }
    }

    @ApiOperation("文件压缩测试")
    @RequestMapping(value = "/getAreaQRCodeTest2",method = RequestMethod.POST)
    public void getAreaQRCodeTest2() throws Exception {
        long id= 3;
        System.out.println("开始");
        //areaService.getAreaQRCode(id);
        System.out.println("结束");
    }

}