package cn.edu.scau.service;

import cn.edu.scau.model.Logistic;
import cn.edu.scau.model.SearchLogisticForm;

import java.util.List;

public interface LogisticService {
    public List<Logistic> getAllLogistic();
    public int addLogistic(Logistic logistic);
    public int delete(long[] ids);
    public List<Logistic> search(SearchLogisticForm form);
    public List<String> getAllStatus();
    public String getExp(String expNo);
}
