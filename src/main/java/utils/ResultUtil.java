package utils;

import entity.Result;
import enums.ResultEnum;

/**
 * @author Zj
 * @date 2019/2/8 13:47
 */
public class ResultUtil {

  public static Result success(ResultEnum resultEnum) {
    Result result = new Result();
    result.setCode(resultEnum.getCode());
    result.setMsg(resultEnum.getMsg());
    return result;
  }

  public static Result error(ResultEnum resultEnum) {
    Result result = new Result();
    result.setCode(resultEnum.getCode());
    result.setMsg(resultEnum.getMsg());
    return result;
  }

  public static Result loginError(ResultEnum resultEnum) {
    Result result = new Result();
    result.setCode(resultEnum.getCode());
    result.setMsg(resultEnum.getMsg());
    return result;
  }
}
