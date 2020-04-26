package cn.edu.scau.util.fileutil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileUpload {
    public static void fileUpload(HttpServletResponse response , String filePath){
        //String filePath = "D:\\zhuhai_project\\grape_tracing\\grape_trace20200408\\grape_tracingback-master\\tree.jpg";
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
            }
//            else if("zip".contains(fileType)){
//                response.setContentType("zip");
//            }
            else{//自动判断下载文件类型
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
}
