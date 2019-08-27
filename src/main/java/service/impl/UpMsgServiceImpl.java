package service.impl;

import dao.UpMsgMapper;
import entity.UpMsg;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import service.UpMsgService;
import utils.HttpUtils;

import javax.servlet.http.HttpSession;
import java.net.ConnectException;
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
  public void insertUpMsg(User user) throws Exception {

      String userNum = user.getUserNum();
      String userName = user.getUserName();
      Integer userClassNum = user.getUserClassNum();
      String num2 = userClassNum.toString();
      //存入区块链,因为防止区块链死掉信息没了，所以把信息也像数据库存一份
      String url = "http://47.107.38.55:8435/addRecord";
      if(utils.sendMsg(url,userName,userNum,num2)){
        //存入数据库
        upMsgMapper.insertUpMsg(userNum, userName,userClassNum);
      }
  }

  @Override
  public List<UpMsg> getUpMsgByUserNum(String userNum) {
    return upMsgMapper.getUpMsgByUserNum(userNum);
  }
}
