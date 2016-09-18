package com.pein.wechat.httpClient;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.*;
import java.security.cert.CertificateException;

public class HttpsRequest {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(HttpsRequest.class);

    public interface ResultListener {
        public void onConnectionPoolTimeoutError();

    }
    //连接超时时间，默认10秒
    private static int socketTimeout = 10000;

    //传输超时时间，默认30秒
    private static int connectTimeout = 30000;

    //请求器的配置
    private static RequestConfig requestConfig;

    //HTTP请求器
    private static CloseableHttpClient httpClient;

    /**
     * 初始化证书信息
     *
     * @param bool
     * @throws IOException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    @SuppressWarnings("deprecation")
    public static void init(boolean bool, String certPath, String certPassword) throws IOException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException {

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(certPath));//加载本地的证书进行https加密传输
        try {
            keyStore.load(instream, certPassword.toCharArray());//设置证书密码
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = null;
        if (bool) {
            // 读取证书
            sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, certPassword.toCharArray())
                    .build();
        } else {
            // 不读取文件的
            sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(null, null)
                    .build();
        }

        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

        httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();

        //根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
    }

    /**
     * 发送报文
     *
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IntrospectionException
     */
    public static String sendPost(String api_url, Object xmlObj) throws Exception {
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(api_url);
            //解决XStream对出现双下划线的bug
            XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
            //将要提交给API的数据对象转换成XML格式数据Post给API
            String postDataXML = xStreamForRequestPostData.toXML(xmlObj);
            log.info("LOG01120: API，POST过去的数据是：{}. ", postDataXML);
            //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
            StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(postEntity);
            //设置请求器的配置
            httpPost.setConfig(requestConfig);
            log.info("LOG01140: executing request {}. ", httpPost.getRequestLine());
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } finally {
            httpPost.abort();
        }
    }

    /**
     * 发送报文
     *
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IntrospectionException
     */
    public static String sendPost(String api_url, String orderNo) throws Exception {
        HttpPost httpPost = null;
        try {

            httpPost = new HttpPost(api_url);
            log.info("LOG01150:API，POST过去的数据是：api_url : {},orderNo :{}.",api_url,orderNo);
            //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
            httpPost.addHeader("Content-Type", "text/xml");
            //设置请求器的配置
            httpPost.setConfig(requestConfig);
            log.info("LOG01160:executing request {} . ", httpPost.getRequestLine());
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpPost.abort();
        }
        return null;
    }


    public int getSocketTimeout() {
        return socketTimeout;
    }


    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }


    public int getConnectTimeout() {
        return connectTimeout;
    }


    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }


    public RequestConfig getRequestConfig() {
        return requestConfig;
    }


    public void setRequestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }


    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }


    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }


}
