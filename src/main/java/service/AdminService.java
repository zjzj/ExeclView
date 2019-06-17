package service;

import entity.Admin;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;

/**
 * @author Zj
 * @date 2019/3/9 16:34
 */
public interface AdminService {

  /**
   * 查询admin
   * @param adminName adminName
   * @param adminPassword adminPassword
   * @param session session
   * @return admin
   */
  Integer getAdminByAdminAndAdminPassword(@Param("adminName") String adminName, @Param("adminPassword") String adminPassword, HttpSession session);

}
