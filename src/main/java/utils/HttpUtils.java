package utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class HttpUtils {


  /**
   * 最大连接数
   */
  private static final int MAX_CONN_TOTAL = 200;
  /**
   * 单个路由的最大连接数，默认是2
   */
  private static final int MAX_CONN_PERROUTE = 20;

  /**
   * 默认超时时间，毫秒
   */
  private static final int DEFAULT_TIMEOUT = 30000;

  /**
   * 默认编码
   */
  private static final String DEFAULT_CHARSET = "UTF-8";

  /**
   * @param url
   * @param paramters
   * @param values
   * @param timeout
   * @return
   * @throws Exception
   */
  public static String post(String url, String[] paramters, String[] values, int timeout) throws Exception {
    CloseableHttpResponse response = null;
    CloseableHttpClient client = null;
    String result = "";
    try {
      PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
      cm.setDefaultMaxPerRoute(MAX_CONN_PERROUTE);
      cm.setMaxTotal(MAX_CONN_TOTAL);
      client = HttpClients.custom().setConnectionManager(cm).build();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      for (int i = 0; i < paramters.length; i++) {
        params.add(new BasicNameValuePair(paramters[i], values[i]));
      }
      HttpPost httppost = new HttpPost(url);
      httppost.setEntity(new UrlEncodedFormEntity(params, Charset.forName(DEFAULT_CHARSET)));

      // 设置请求超时，连接超时和数据传输超时
      RequestConfig requestConfig = RequestConfig.custom()
        .setConnectionRequestTimeout(timeout)
        .setConnectTimeout(timeout)
        .setSocketTimeout(timeout).build();
      httppost.setConfig(requestConfig);

      response = client.execute(httppost);
      int statusCode = response.getStatusLine().getStatusCode();
      if (statusCode == HttpStatus.SC_OK) {
        HttpEntity responseEntity = response.getEntity();
        result = EntityUtils.toString(responseEntity, DEFAULT_CHARSET);
      } else {
        throw new RuntimeException("Response status:" + statusCode);
      }
    } finally {
      if (response != null) {
        response.close();
      }
      if (client != null) {
        client.close();
      }
    }
    return result;
  }

  /**
   * 参数为json的post请求
   * @param url url
   * @param jsonData json
   * @return 1
   * @throws Exception e
   */
  public static String post(String url, String jsonData, int timeout) throws Exception {
    CloseableHttpResponse response = null;
    CloseableHttpClient client = null;
    String result = "";
    try {
      PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
      cm.setDefaultMaxPerRoute(MAX_CONN_PERROUTE);
      cm.setMaxTotal(MAX_CONN_TOTAL);
      client = HttpClients.custom().setConnectionManager(cm).build();

      HttpPost httppost = new HttpPost(url);
      HttpEntity requestEntity = new StringEntity(jsonData, Charset.forName(DEFAULT_CHARSET));
      httppost.setEntity(requestEntity);

      // 设置超时时间
      RequestConfig requestConfig = RequestConfig.custom()
        .setConnectionRequestTimeout(timeout).setConnectTimeout(timeout)
        .setSocketTimeout(timeout).build();
      httppost.setConfig(requestConfig);

      response = client.execute(httppost);
      int statusCode = response.getStatusLine().getStatusCode();
      if (statusCode == HttpStatus.SC_OK) {
        HttpEntity responseEntity = response.getEntity();
        result = EntityUtils.toString(responseEntity, DEFAULT_CHARSET);
      } else {
        throw new RuntimeException("Response status:" + statusCode);
      }
    } finally {
      if (response != null) {
        response.close();
      }
      if (client != null) {
        client.close();
      }
    }
    return result;
  }

  /**
   * 通过header认证授权接口
   * @param url
   * @param authorization
   * @param timeout
   * @return
   * @throws Exception
   */
  public static String postByAuth(String url, String authorization, int timeout) throws Exception {
    CloseableHttpResponse response = null;
    CloseableHttpClient client = null;
    String result = "";
    try {
      PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
      cm.setDefaultMaxPerRoute(MAX_CONN_PERROUTE);
      cm.setMaxTotal(MAX_CONN_TOTAL);
      client = HttpClients.custom().setConnectionManager(cm).build();

      HttpPost httppost = new HttpPost(url);
      httppost.setHeader("Authorization", authorization);

      // 设置超时时间
      RequestConfig requestConfig = RequestConfig.custom()
        .setConnectionRequestTimeout(timeout)
        .setConnectTimeout(timeout)
        .setSocketTimeout(timeout).build();
      httppost.setConfig(requestConfig);

      response = client.execute(httppost);
      int statusCode = response.getStatusLine().getStatusCode();
      if (statusCode == HttpStatus.SC_OK) {
        HttpEntity responseEntity = response.getEntity();
        result = EntityUtils.toString(responseEntity, DEFAULT_CHARSET);
      } else {
        throw new RuntimeException("Response status:" + statusCode);
      }
    } finally {
      if (response != null) {
        response.close();
      }
      if (client != null) {
        client.close();
      }
    }
    return result;
  }

  /**
   * 支持contentType的请求
   * @param url
   * @param data
   * @return
   * @throws Exception
   */
  public static String post(String url, String data, String contentType) throws Exception {
    CloseableHttpResponse response = null;
    CloseableHttpClient httpclient = null;
    String result = "";
    try {
      PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
      cm.setDefaultMaxPerRoute(MAX_CONN_PERROUTE);
      cm.setMaxTotal(MAX_CONN_TOTAL);
      httpclient = HttpClients.custom().setConnectionManager(cm).build();
      HttpPost httppost = new HttpPost(url);
      // 设置参数
      StringEntity reqEntity = new StringEntity(data);
      reqEntity.setContentType(contentType);

      // 设置超时时间
      RequestConfig requestConfig = RequestConfig.custom()
        .setConnectionRequestTimeout(DEFAULT_TIMEOUT)
        .setConnectTimeout(DEFAULT_TIMEOUT)
        .setSocketTimeout(DEFAULT_TIMEOUT).build();
      httppost.setConfig(requestConfig);

      httppost.setEntity(reqEntity);
      response = httpclient.execute(httppost);
      int statusCode = response.getStatusLine().getStatusCode();
      if (statusCode == HttpStatus.SC_OK) {
        HttpEntity responseEntity = response.getEntity();
        result = EntityUtils.toString(responseEntity, DEFAULT_CHARSET);
      } else {
        throw new RuntimeException("Response status:" + statusCode);
      }
    } finally {
      if (response != null) {
        response.close();
      }
      if (httpclient != null) {
        httpclient.close();
      }
    }
    return result;
  }

  /**
   * 支持https请求
   * @param url
   * @param data
   * @param contentType
   * @return
   * @throws Exception
   */
  @SuppressWarnings("deprecation")
  public static String postByHttps(String url, String data, String contentType) throws Exception {
    HttpClient client = null;
    PostMethod post = null;
    try {
      client = new HttpClient();
      post = new PostMethod(url);
      post.setRequestHeader("Authorizatimon", "no-check-certificate");
      post.setRequestHeader("Content-type", contentType);
      StringRequestEntity requestEntity = new StringRequestEntity(data);
      post.setRequestEntity(requestEntity);
      client.setTimeout(DEFAULT_TIMEOUT);
      client.setConnectionTimeout(DEFAULT_TIMEOUT);
      int status = client.executeMethod(post);
      if (status == HttpStatus.SC_OK) {
        return new String(post.getResponseBody(), DEFAULT_CHARSET);
      } else {
        throw new RuntimeException("Response status:" + status);
      }
    } finally {
      if (post != null) {
        post.abort();
      }
    }
  }





}
