package controller;

import entity.Result;
import entity.UpMsg;
import entity.User;
import enums.ResultEnum;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.UpMsgService;
import service.UserService;
import utils.HttpUtils;
import utils.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Zj
 * @date 2019/4/6 23:41
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

  private final
  UserService userService;

  @Autowired
  public UserController(UserService userService, UpMsgService upMsgService) {
    this.userService = userService;
    this.upMsgService = upMsgService;
  }

  private final
  UpMsgService upMsgService;

  @Autowired
  private HttpUtils utils;



  /**
   * 登录
   * @param userNum userName
   * @param userPassword userPassword
   * @param session session
   * @return 是否成功
   */
  @PostMapping(value = "/login")
  public Result login(@Param("userNum") String userNum, @Param("userPassword") String userPassword, HttpSession session) {
    userService.getUserByUserNumAndUserPassword(userNum, userPassword, session);
    if (userService.getUserByUserNumAndUserPassword(userNum, userPassword, session) != null) {
      return ResultUtil.success(ResultEnum.USER_LOGIN_SUCCESS_1);
    } else {
      return ResultUtil.error(ResultEnum.USER_LOGIN_SUCCESS_2);
    }
  }

  /**
   * 登出
   * @param session session
   * @return 是否成功
   */
  @GetMapping(value = "/logout")
  public Result logout(HttpSession session) {
    try {
      session.invalidate();
      return ResultUtil.success(ResultEnum.LOGOUT_SUCCESS);
    } catch (Exception e) {
      return ResultUtil.error(ResultEnum.LOGOUT_FAIL);
    }
  }

  /**
   * 上传文件以及将信息发送给区块链
   * @param filename 文件名
   * @param request request
   * @param session session
   * @return 是否成功
   * @throws Exception 异常
   */
  @PostMapping(value = "/upload")
  public Result upload1(@RequestParam(value = "files", required = false) MultipartFile filename,
                        HttpServletRequest request, HttpSession session) throws Exception {

    User user = (User) session.getAttribute("user");
    Integer num = user.getUserClassNum();
    String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    File file = new File(request.getSession().getServletContext().getRealPath("/"));
    String parent = file.getParent();
    //向服务器存入两份文件，一份当作最新文件，一份当作历史文件
    File newFile = new File(parent + "/upload", num.toString() + ".xlsx");
    File saveFile = new File(parent + "/upload", time + "-" +num.toString() + ".xlsx");
    try {
      upMsgService.insertUpMsg(user);
      filename.transferTo(newFile);
      filename.transferTo(saveFile);
      return ResultUtil.success(ResultEnum.UPLOAD_SUCCESS);
    } catch (IOException e){
      return ResultUtil.error(ResultEnum.UPLOAD_FALL);
    }
  }

  /**
   * 获取课程号
   *
   * @param session session
   * @return 课程号
   */
  @GetMapping(value = "/session")
  public Integer getSession(HttpSession session) {
    User user = (User) session.getAttribute("user");
    return user.getUserClassNum();
  }

  @GetMapping(value = "/message")
  public List<UpMsg> getUpMsgByUserNum(HttpSession session) {
    User user = (User) session.getAttribute("user");
    String userNum = user.getUserNum();
    return upMsgService.getUpMsgByUserNum(userNum);
  }


}
