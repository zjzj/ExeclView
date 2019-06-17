package entity;

import lombok.Data;

/**
 * @author Zj
 * @date 2019/2/8 13:43
 */
@Data
public class Result {

  /**
   * 状态码
   */
  private Integer code;

  /**
   * 提示信息
   */
  private String msg;

}
