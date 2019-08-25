package service;

import entity.UpMsg;
import entity.User;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Zj
 * @date 2019/4/7 12:04
 */
public interface UpMsgService {

  /**
   * 插入一条信息
   */
  void insertUpMsg(User user) throws Exception;

  List<UpMsg> getUpMsgByUserNum(@Param("userNum") String userNum);

}
