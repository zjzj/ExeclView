package entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Zj
 * @date 2019/4/3 20:55
 */
@Data
public class UpMsg {

  private Integer upId;

  private String userNum;

  private String userName;

  private Integer userClassNum;

  private Timestamp upTime;

}
