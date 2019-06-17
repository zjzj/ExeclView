package service;

import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;

/**
 * @author Zj
 * @date 2019/4/6 11:56
 */
public interface UserService {

  /**
   * 查询user登录
   * @param userNum 学号
   * @param userPassword 密码
   * @param session  session
   * @return 成功的条数
   */
  String getUserByUserNumAndUserPassword(@Param("userNum") String userNum, @Param("userPassword") String userPassword, HttpSession session);

}
