package enums;

import lombok.Getter;

/**
 * @author Zj
 * @date 2019/2/8 13:34
 */
@Getter
public enum ResultEnum {


  /**
   * 上传成功
   */
  UPLOAD_SUCCESS(1,"上传成功"),

  /**
   * 上传失败
   */
  UPLOAD_FALL(2,"上传失败"),

  LOGIN_SUCCESS(1,"登录成功"),

  LOGIN_FAIL(2,"登录失败"),

  LOGOUT_SUCCESS(1,"注销成功"),

  LOGOUT_FAIL(2,"注销失败"),

  /**
   * 登录1班
   */
  USER_LOGIN_SUCCESS_1(1,"登录成功"),

  /**
   * 登录2班
   */
  USER_LOGIN_SUCCESS_2(2,"登录2班"),

  /**
   * 登录失败
   */
  USER_LOGIN_SUCCESS_3(3,"登录失败");

  private Integer code;

  private String msg;

  ResultEnum(Integer code,String msg){
    this.code = code;
    this.msg = msg;
  }
}
