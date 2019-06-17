package service;

import entity.UpMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Zj
 * @date 2019/4/7 12:04
 */
public interface UpMsgService {

  /**
   * 插入一条信息
   * @param userNum 学号
   * @param userName 姓名
   */
  void insertUpMsg(@Param("userNum") String userNum, @Param("userName") String userName,@Param("userClassNum") Integer userClassNum);

  List<UpMsg> getUpMsgByUserNum(@Param("userNum") String userNum);

}
