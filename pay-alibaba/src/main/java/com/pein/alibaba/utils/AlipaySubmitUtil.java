package com.pein.alibaba.utils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import com.pein.alibaba.config.*;
import com.pein.alibaba.key.DirectPayRequestKey;
import com.pein.alibaba.utils.httpClient.HttpProtocolHandler;
import com.pein.alibaba.utils.httpClient.HttpRequest;
import com.pein.alibaba.utils.httpClient.HttpResponse;
import com.pein.alibaba.utils.httpClient.HttpResultType;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;


public class AlipaySubmitUtil {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AlipaySubmitUtil.class);

    /**
     * 组装formMD5
     * @param sParamTemp
     * @return
     */
    public static String bulidFormMd5(Map<String,String> sParamTemp){

        String aliRs = null;

        // 除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParamTemp);

        // 生产签名结果
        sPara.put("sign", AlipayCore.buildMysign(sPara,sParamTemp.get(DirectPayRequestKey.SIGN)));

        // 签名结果与签名方式加入请求提交参数组中
        if( !sPara.get("service").equals("alipay.wap.trade.create.direct") && ! sPara.get("service").equals("alipay.wap.auth.authAndExecute")) {
            sPara.put(DirectPayRequestKey.SIGN_TYPE, sParamTemp.get(DirectPayRequestKey.SIGN_TYPE));
        }

        aliRs = com.pein.alibaba.utils.AlipayCoreUtil.getMapToString(sPara, "UTF-8");
        log.info("LOG_QW00140:  支付宝请求参数组装后MD5：{}", aliRs);

        return aliRs;
    }

    /**
     * 组装formRAS
     * @param sParamTemp
     * @return
     */
    public static String buildFormRSA(Map<String, String> sParamTemp) {

        String aliRs = null;

        // 除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParamTemp);

        //生成签名结果
        String mysign = AlipayCore.buildMysign_RSA(sPara,sParamTemp.get(DirectPayRequestKey.SIGN),"UTF-8");

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", sParamTemp.get(DirectPayRequestKey.SIGN));
        sPara.put("sign_type", sParamTemp.get(DirectPayRequestKey.SIGN_TYPE));

        log.info("LOG_QW00110: 支付宝请求参数RSA：{}",sPara);
        aliRs = AlipayCoreUtil.getMapToString(sPara, "UTF-8");
        log.info("LOG_QW00130: 支付宝请求参数组装后RSA：{}",aliRs);

        return aliRs;
    }

    /**
     * 组装formMD5
     * @param sParamTemp
     * @param prikeyMD5
     * @return
     * @throws IOException
     * @throws HttpException
     */
    public static String bulidTradeQuery(Map<String,String> sParamTemp, String prikeyMD5) throws IOException{

        String aliRs = null;

        // 除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParamTemp);

        // 生产签名结果
        sPara.put("sign", AlipayCore.buildMysign(sPara,prikeyMD5));

        // 签名结果与签名方式加入请求提交参数组中
        if( !sPara.get("service").equals("alipay.wap.trade.create.direct") && ! sPara.get("service").equals("alipay.wap.auth.authAndExecute")) {
            sPara.put("sign_type", AlipayConfig.SIGN_MD5);
        }

        //待请求参数数组
        HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();

        HttpRequest request = new HttpRequest(HttpResultType.BYTES);
        //设置编码集
        request.setCharset(Configure.getInputCharset());

        request.setParameters(generatNameValuePair(sPara));
        request.setUrl(Configure.getAli_url()+"_input_charset="+Configure.getInputCharset());

        HttpResponse response = httpProtocolHandler.execute(request,"","");
        if (response == null) {
            return null;
        }

        aliRs = response.getStringResult();

        return aliRs;
    }

    /**
     * 退款交易
     * @param sParamTemp
     * @param logger
     * @param prikeyMD5
     * @return
     */
    public static String bulidRefund(Map<String,String> sParamTemp, Logger logger, String prikeyMD5) {

        //除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParamTemp);
        //生成签名结果
        String mysign = AlipayCore.buildMysign(sPara,prikeyMD5);

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        if(! sPara.get("service").equals("alipay.wap.trade.create.direct") && ! sPara.get("service").equals("alipay.wap.auth.authAndExecute")) {
            sPara.put("sign_type", AlipayConfig.SIGN_MD5);
        }

        logger.info("支付宝请求参数："+sPara);

//        return HttpTookit.doGet(Configure.getAli_url(),AlipayCoreUtil.getMapToString(sPara,Configure.getInputCharset()), Configure.getInputCharset());
        return null;
    }

    /**
     * MAP类型数组转换成NameValuePair类型
     * @param properties  MAP类型数组
     * @return NameValuePair类型数组
     */
    private static NameValuePair[] generatNameValuePair(Map<String, String> properties) {
        NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            nameValuePair[i++] = new NameValuePair((String)entry.getKey(), (String)entry.getValue());
        }
        return nameValuePair;
    }

    /**
     * 创建退款详细信息
     * @param instOrderNO
     * @param amount
     * @return
     */
    public static String createDetailData( String instOrderNO, String amount ){
        StringBuffer sb = new StringBuffer();
        sb.append(instOrderNO);
        sb.append("^");
        sb.append(amount);
        sb.append("^");
        sb.append("协商退款");
        return sb.toString();
    }

}
