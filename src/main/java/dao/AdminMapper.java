package dao;

import entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Zj
 * @date 2019/3/8 17:45
 */
@Repository
public interface AdminMapper {

  /**
   * 获取一条信息
   * @param adminName 名字
   * @param adminPassword 密码
   * @return 信息
   */
  Admin getAdminByAdminNameAndAdminPassword(@Param("adminName") String adminName,@Param("adminPassword") String adminPassword);

}
