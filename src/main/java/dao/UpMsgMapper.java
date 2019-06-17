package dao;

import entity.UpMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zj
 * @date 2019/4/3 21:04
 */
@Repository
public interface UpMsgMapper {


  /**
   * 上传一条信息
   * @param userNum 学号
   * @param userName 姓名
   * @param userClassNum 课程号
   * @return 成功的条数
   */
  int insertUpMsg(@Param("userNum") String userNum,@Param("userName") String userName,@Param("userClassNum") Integer userClassNum);

  List<UpMsg> getUpMsgByUserNum(@Param("userNum") String userNum);

}
