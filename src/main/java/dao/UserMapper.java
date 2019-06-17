package dao;

import entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zj
 * @date 2019/4/3 21:04
 */
@Repository
public interface UserMapper {

  /**
   * 查询用户，用来登录
   * @param userNum 学号
   * @param userPassword 密码
   * @return 用户
   */
  User getUserByUserNumAndUserPassword(@Param("userNum") String userNum,@Param("userPassword") String userPassword);

  /**
   * 插入一条用户
   * @param userNum 学号
   * @param userName 名字
   * @param userPhone 电话
   * @param userClassNum 所在的课程号
   * @param userClass  班级
   * @param userPassword 密码
   * @return 成功条数
   */
  int insertUser(@Param("userNum") String userNum,@Param("userName") String userName,
                 @Param("userPhone") String userPhone,@Param("userClassNum") Integer userClassNum,
                 @Param("userClass") String userClass,@Param("userPassword") String userPassword);

  /**
   * 列出所有用户
   * @return 用户列表
   */
  List<User> listAllUser();

  /**
   * 根据课程编号查询课代表
   * @param userClassNum 课程编号
   * @return 用户列表
   */
  List<User> getUserByClassNum(@Param("userClassNum") Integer userClassNum);

}
