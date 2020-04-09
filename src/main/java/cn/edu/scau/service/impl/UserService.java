package cn.edu.scau.service.impl;

import cn.edu.scau.dao.UserDao;
import cn.edu.scau.dao.UserRoleDao;
import cn.edu.scau.model.Role;
import cn.edu.scau.model.User;
import cn.edu.scau.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.loadUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("用户名不存在");
        user.setRoles(userDao.getUserRolesById(user.getId()));
        return user;
    }

    @Transactional
    public boolean updateUserRole(Integer userId, Integer[] rids) {
        userRoleDao.deleteByPrimaryKey(userId);
        return userRoleDao.addRole(userId, rids) == rids.length;
    }

    public Integer deleteByUserId(Integer id) {
        return userDao.deleteByPrimaryKey(id);
    }

    public boolean updateUserPassword(String oldpass, String pass, Integer userId) {
        User user = userDao.selectByPrimaryKey(userId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldpass, user.getPassword())) {
            String encodePass = encoder.encode(pass);
            Integer result = userDao.updatePasswd(userId, encodePass);
            if (result == 1) {
                return true;
            }
        }
        return false;
    }

    public Integer updateUserface(String url, Integer id) {
        return userDao.updateUserface(url, id);
    }

    public List<User> getAllUsers() {
        return userDao.selectUsersWithRoles();
    }

    public List<User> getUserByKeywords(String keywords) {
        return userDao.selectUserWithRolesByKeywords(keywords);
    }

    public int updateUser(User user) {
        return userDao.updateById(user);
    }

    public int addUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setEnabled(true);
        user.setPassword(encoder.encode("123456"));
        user.setDate(new Date());
        return userDao.insertUser(user);
    }

    public boolean updatePwd(String oldpwd, String pwd, Integer userId) {
        User user = userDao.selectByPrimaryKey(userId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldpwd, user.getPassword())) {
            String newPwd = encoder.encode(pwd);
            Integer integer = userDao.updatePasswd(userId, newPwd);
            return integer == 1;
        }
        return false;
    }
}
