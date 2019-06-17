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
import utils.ResultUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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

  @PostMapping(value = "/login")
  public Result login(@Param("userNum") String userNum, @Param("userPassword") String userPassword, HttpSession session) {
    userService.getUserByUserNumAndUserPassword(userNum, userPassword, session);
    if (userService.getUserByUserNumAndUserPassword(userNum, userPassword, session) != null) {
      return ResultUtil.success(ResultEnum.USER_LOGIN_SUCCESS_1);
    } else {
      return ResultUtil.error(ResultEnum.USER_LOGIN_SUCCESS_3);
    }
  }

  @GetMapping(value = "/logout")
  public Result logout(HttpSession session) {
    try {
      session.invalidate();
      return ResultUtil.success(ResultEnum.LOGOUT_SUCCESS);
    } catch (Exception e) {
      return ResultUtil.error(ResultEnum.LOGOUT_FAIL);
    }
  }

  @PostMapping(value = "/upload")
  public Result upload1(@RequestParam(value = "files", required = false) MultipartFile filename,
                        HttpServletRequest request, HttpSession session) throws Exception {

    User user = (User) session.getAttribute("user");
    Integer num = user.getUserClassNum();
    String userNum = user.getUserNum();
    String userName = user.getUserName();
    Integer userClassNum = user.getUserClassNum();
    Integer userClassNum2 = user.getUserClassNum();
    String num2 = userClassNum2.toString();

    Date date = new Date();
    date.getTime();
    String time = date.toString();
    File file = new File(request.getSession().getServletContext().getRealPath("/"));
    String parent = file.getParent();
    String savedDir = request.getSession().getServletContext().getRealPath("/");
    File newFile = new File(parent + "/upload", num + ".xlsx");
    File saveFile = new File(parent + "/upload", time + num + ".xlsx");
    System.out.println(savedDir);
    PostMethod postMethod = new PostMethod("http://10.2.132.171:8435/addRecord");
    postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
    NameValuePair[] data = {
      new NameValuePair("upName", userName),
      new NameValuePair("upNum", userNum),
      new NameValuePair("classNum", num2),
      new NameValuePair("upTime", time)
    };
    postMethod.setRequestBody(data);
    String result = postMethod.getResponseBodyAsString();
    org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
    try {
      httpClient.executeMethod(postMethod);
      filename.transferTo(newFile);
      filename.transferTo(saveFile);
      upMsgService.insertUpMsg(userNum, userName, userClassNum,session);
      return ResultUtil.success(ResultEnum.UPLOAD_SUCCESS);
    } catch (IOException e) {
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
