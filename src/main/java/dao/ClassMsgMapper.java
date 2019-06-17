package dao;

import entity.ClassMsg;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zj
 * @date 2019/4/3 21:01
 */

@Repository
public interface ClassMsgMapper {

  /**
   * 增加课程
   * @param classMsg 课程信息
   * @return 成功的条数
   */
  int insertClassMsg(ClassMsg classMsg);

  /**
   * 查询所有的课程列表
   * @return 课程列表
   */
  List<ClassMsg> listAllClassMsg();

}
