package service.impl;

import dao.UpMsgMapper;
import entity.UpMsg;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UpMsgService;
import utils.HttpUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Zj
 * @date 2019/4/7 12:04
 */
@Service
public class UpMsgServiceImpl implements UpMsgService {

  @Autowired
  UpMsgMapper upMsgMapper;

  @Autowired
  private HttpUtils utils;

  @Override
  public void insertUpMsg(User user) {
    String userNum = user.getUserNum();
    String userName = user.getUserName();
    Integer userClassNum = user.getUserClassNum();
    String num2 = userClassNum.toString();
    //存入数据库
    upMsgMapper.insertUpMsg(userNum, userName,userClassNum);
    //存入区块链
    String url = "http://10.4.208.76:8435/addRecord";
    utils.sendMsg(url,userName,userNum,num2);

  }

  @Override
  public List<UpMsg> getUpMsgByUserNum(String userNum) {
    return upMsgMapper.getUpMsgByUserNum(userNum);
  }
}
