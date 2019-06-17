package controller;

import entity.Admin;
import entity.Result;
import enums.ResultEnum;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.AdminService;
import utils.ResultUtil;

import javax.servlet.http.HttpSession;

/**
 * @author Zj
 * @date 2019/3/9 16:40
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

  private final
  AdminService adminService;

  @Autowired
  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }

  @PostMapping(value = "/login")
  public Result login(@Param("adminName") String adminName, @Param("adminPassword") String adminPassword, HttpSession session){
    Integer adminId = adminService.getAdminByAdminAndAdminPassword(adminName,adminPassword,session);
   if(adminId != null){
     session.setAttribute("adminId",adminId);
     return ResultUtil.success(ResultEnum.LOGIN_SUCCESS);
   }else{
     return ResultUtil.error(ResultEnum.LOGIN_FAIL);
   }
  }

}
