package entity;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Min;

/**
 * @author Zj
 * @date 2019/4/3 20:46
 */
@Data
public class User {

  private Integer userId;

  private String userNum;

  private String userName;

  private String userPhone;

  private Integer userClassNum;

  private String userClass;

  private String userPassword;

}
