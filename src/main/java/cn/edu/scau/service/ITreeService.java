package cn.edu.scau.service;

import cn.edu.scau.model.SearchTreeForm;
import cn.edu.scau.model.Tree;
import cn.edu.scau.model.TreeCategory;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ITreeService {

    List<Tree> getAllTrees();

    List<TreeCategory> getAllCategory();

    void addTrees(Tree tree);

    void removeTrees(long[] ids);

    void editTree(Tree tree);

    List<Tree> findTrees(SearchTreeForm tree);

    public boolean getTreeQRCode(long id, HttpServletResponse response) throws Exception;
}
