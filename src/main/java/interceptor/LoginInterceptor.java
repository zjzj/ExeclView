package interceptor;

import entity.Admin;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author Zj
 * @date 2019/3/8 17:18
 */

public class LoginInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    throws Exception {
    // TODO Auto-generated method stub

  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse reponse, Object handler, ModelAndView modelAndView)
    throws Exception {
    // TODO Auto-generated method stub
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    // TODO Auto-generated method stub
    HttpSession session = request.getSession(false);
    User user =(User) session.getAttribute("user");
    if(user == null) {
      logger.info("未登录，session为空，不放行");
      System.out.println(session);
      response.sendRedirect(request.getContextPath()+"/");
      return false;
    }
    else {
      return true;
    }

  }

}

