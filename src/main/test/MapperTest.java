import dao.AdminMapper;
import dao.ClassMsgMapper;
import dao.UpMsgMapper;
import dao.UserMapper;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Zj
 * @date 2019/2/3 14:00
 */

@WebAppConfiguration("src/main/resources")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class MapperTest {

  @Autowired
  ClassMsgMapper classMsgMapper;

  @Autowired
  UpMsgMapper upMsgMapper;

  @Autowired
  UserMapper userMapper;

  @Autowired
  AdminMapper adminMapper;

  @Test
  public void testMapper() throws Exception {
    //messageMapper.insertMessage("张三飒飒","1");
    // System.out.println(messageMapper.getLastMessage());
    // System.out.println(adminMapper.getAdminByAdminNameAndAdminPassword("admin1","123"));
    //System.out.println(messageMapper.listAllMessage2());
    //userMapper.insertUser(123,"zj","1234567","123","1603","123");
    //   System.out.println(userMapper.listAllUser());
    // System.out.println(userMapper.getUserByClassNum("123"));
    // System.out.println(userMapper.getUserByUserNumAndUserPassword("123", "123"));
    // upMsgMapper.insertUpMsg("2016","zj");
    //  System.out.println(upMsgMapper.getUpMsgByUserNum("123"));

    String postURL;
    PostMethod postMethod = null;
    postMethod = new PostMethod("http://10.4.208.76:8435/addRecord");
    postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//参数设置，需要注意的就是里边不能传NULL，要传空字符串
    NameValuePair[] data = {
      new NameValuePair("upName", "朱熙龙"),
      new NameValuePair("upNum", "20170101"),
      new NameValuePair("classNum", "19001"),
      new NameValuePair("upTime", "2019-06-13 08:21:42"),

    };

    postMethod.setRequestBody(data);

    org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
    String result = postMethod.getResponseBodyAsString();
    int response = httpClient.executeMethod(postMethod); // 执行POST方法


//    Date date = new Date();
//    date.getTime();
//    Calendar calendar = Calendar.getInstance();
//    calendar.setTime(date);
//    calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 16);
//    System.out.println(calendar.getTime());

  }
}
