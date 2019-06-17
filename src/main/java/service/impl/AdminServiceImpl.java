package service.impl;

import dao.AdminMapper;
import entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AdminService;

import javax.servlet.http.HttpSession;

/**
 * @author Zj
 * @date 2019/3/9 16:34
 */
@Service
public class AdminServiceImpl implements AdminService {

  @Autowired
  AdminMapper adminMapper;

  @Override
  public Integer getAdminByAdminAndAdminPassword(String adminName, String adminPassword, HttpSession session) {
    Admin admin = adminMapper.getAdminByAdminNameAndAdminPassword(adminName, adminPassword);
    if(admin != null){
      Integer adminId = admin.getAdminId();
      return adminId;
    }else{
      return null;
    }
  }
}
