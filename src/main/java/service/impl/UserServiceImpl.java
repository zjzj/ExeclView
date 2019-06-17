package service.impl;

import dao.UserMapper;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import javax.servlet.http.HttpSession;

/**
 * @author Zj
 * @date 2019/4/6 11:56
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserMapper userMapper;


  @Override
  public String getUserByUserNumAndUserPassword(String userNum, String userPassword, HttpSession session) {
    User user = userMapper.getUserByUserNumAndUserPassword(userNum, userPassword);
    if(user != null){
      session.setAttribute("user",user);
      return userNum;
    }else {
      return null;
    }
  }
}
