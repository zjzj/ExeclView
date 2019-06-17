package service.impl;

import dao.UpMsgMapper;
import entity.UpMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UpMsgService;

import java.util.List;

/**
 * @author Zj
 * @date 2019/4/7 12:04
 */
@Service
public class UpMsgServiceImpl implements UpMsgService {

  @Autowired
  UpMsgMapper upMsgMapper;

  @Override
  public void insertUpMsg(String userNum, String userName,Integer userClassNum) {
    upMsgMapper.insertUpMsg(userNum, userName,userClassNum);
    
  }

  @Override
  public List<UpMsg> getUpMsgByUserNum(String userNum) {
    return upMsgMapper.getUpMsgByUserNum(userNum);
  }
}
