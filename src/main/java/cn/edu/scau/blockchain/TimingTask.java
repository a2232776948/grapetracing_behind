package cn.edu.scau.blockchain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimingTask {

    @Autowired
    BlockchainService service;

    @Scheduled(cron = "0 0 1 * * *")
    public void executeFileDownLoadTask() {
        System.out.println("...........上链任务启动...........");
        service.push();
        System.out.println("...........上链任务结束...........");
    }

}
