package utils;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Zj
 * @date 2019/8/13 14:47
 */

public class HttpUtils {
   public boolean sendMsg(String url,String userName,String userNum,String classNum,String time){
     PostMethod postMethod = new PostMethod(url);
     postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
     NameValuePair[] data = {
       new NameValuePair("upName", userName),
       new NameValuePair("upNum", userNum),
       new NameValuePair("classNum", classNum),
       new NameValuePair("upTime", time)
     };
     postMethod.setRequestBody(data);
     org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
     try {
       httpClient.executeMethod(postMethod);
       return true;
     } catch (IOException e) {
       e.printStackTrace();
       return false;
     }
   }

}
