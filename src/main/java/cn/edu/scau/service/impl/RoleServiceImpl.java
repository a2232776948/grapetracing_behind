package cn.edu.scau.service.impl;

import cn.edu.scau.dao.RoleDao;
import cn.edu.scau.model.Role;
import cn.edu.scau.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    RoleDao roleDao;

    public List<Role> getAllRoles() {
        return roleDao.selectAllRoles();
    }
}
