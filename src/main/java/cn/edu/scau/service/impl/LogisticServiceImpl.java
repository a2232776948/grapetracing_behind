package cn.edu.scau.service.impl;

import cn.edu.scau.dao.LogisticDao;
import cn.edu.scau.model.Logistic;
import cn.edu.scau.model.SearchLogisticForm;
import cn.edu.scau.service.LogisticService;
import cn.edu.scau.util.ExpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogisticServiceImpl implements LogisticService {
    @Autowired
    LogisticDao logisticDao;

    @Override
    public List<Logistic> getAllLogistic() {
        Map<String,String> statusDes=new HashMap<>();
        statusDes.put("0","暂无物流信息");
        statusDes.put("1","已揽收");
        statusDes.put("2","在途中");
        statusDes.put("3","签收");
        statusDes.put("4","问题件");
        List<Logistic> logis = logisticDao.getLogiStaNoSign();
        for(Logistic logi : logis){
            String expStatus = ExpUtil.getExpStatus(logi.getExpressNumber());
            if(!expStatus.equals("错误")){
                logi.setStatus(statusDes.get(expStatus));
                logisticDao.update(logi);
            }
        }
        return logisticDao.getAllLogistic();
    }

    @Override
    public int addLogistic(Logistic logistic) {
        logistic.setStatus("暂无物流信息");
        int count = logisticDao.addLogistic(logistic);
        return count;
    }

    @Override
    public int delete(long[] ids) {
        int i = logisticDao.delete(ids);
        return i;
    }

    @Override
    public List<Logistic> search(SearchLogisticForm form) {
        return logisticDao.search(form);
    }

    @Override
    public List<String> getAllStatus() {
        return logisticDao.getAllStatus();
    }

    @Override
    public String getExp(String expId) {
        return ExpUtil.getExpInfo(expId);
    }
}
