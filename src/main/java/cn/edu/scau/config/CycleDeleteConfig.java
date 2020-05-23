package cn.edu.scau.config;

import cn.edu.scau.util.qrcode.QRCodeUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;

/**
 * 没周的星期三凌晨2点删除产生的二维码文件，避免无谓的硬盘消耗
 */
@Configuration("myScheduled")
@EnableScheduling
public class CycleDeleteConfig {
    /**
     * 获得二维码存放的目录
     * @return
     */
    private String getBasePath(){
        String areaQrPath = "";
        try{
            areaQrPath = QRCodeUtil.getbaseURL()+"static/upload/qrimage";
        }catch (Exception e){
            System.out.println("QRCodeUtil.getbaseURL()在测试环境无效");
        }
        return areaQrPath;
    }

    private boolean delFile(File file) {
        if (!file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
        }
        return file.delete();
    }

    //每10秒执行一次
    //@Scheduled(cron="0/59 * * * * ?")

    @Scheduled(cron = "0 0 2 ? * WED ")
    public void timmer() {
        String path = getBasePath();
        System.out.println("path: "+getBasePath());
        System.out.println("开始删除二维码文件");
        File file = new File(path);
        delFile(file);
        System.out.println("完成删除二维码文件");
    }


}
