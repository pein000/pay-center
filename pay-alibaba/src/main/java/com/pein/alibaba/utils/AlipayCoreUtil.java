/**
 * Copyright 2015 netfinworks.com, Inc. All rights reserved.
 */
package com.pein.alibaba.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.pein.alibaba.config.AlipayConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


/**
 * <p>注释</p>
 * @author zhangliang
 * @version $Id: AlipayCoreUtil.java, v 0.1 2015-2-6 下午2:43:03 zhangliang Exp $
 */
public class AlipayCoreUtil {
    public static String buildForm(Map<String, String> sParaTemp, Logger logger,
                                   Properties properties) {
        // 待请求参数
        String sPara = buildRequestPara(sParaTemp, logger, properties);
        return sPara;
    }

    /**
     * 生成要请求给支付宝的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    public static String buildRequestPara(Map<String, String> sParaTemp, Logger logger,
                                          Properties properties) {
        String result = "";
        String inputCharSet = properties.getProperty("INPUT_CHARSET");
        //除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
        //把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
        String prestr = AlipayCore.createLinkString(sPara);
        //生成签名结果
        String mysign = buildMysign(prestr, properties, inputCharSet);

        //签名结果与签名方式加入请求提交参数组中
        //        sPara.put("sign", mysign);
        result = prestr + "&sign_type=" + AlipayConfig.SIGN_MD5 + "&sign=" + mysign;
        //        if(! sPara.get("service").equals("alipay.wap.trade.create.direct") && ! sPara.get("service").equals("alipay.wap.auth.authAndExecute")) {
        //            sPara.put("sign_type", AlipayConfig.SIGN_MD5);
        //        }
        logger.info("支付宝请求参数：" + result);
        return result;
    }

    /**
     * 生成签名结果
     * @param encode 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildMysign(String prestr, Properties properties, String encode) {
        //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        System.out.println("ALIPAYFundChannelKey.MD5_key=="
                + properties.getProperty("MD5_key"));

        //解码异步回调uri
        String realUri = decodeUri(prestr, encode);

        String mysign = com.pein.alibaba.utils.AlipayMd5Encrypt.sign(realUri,
                properties.getProperty("MD5_key"), encode);
        return mysign;
    }

    /**
     * 转码
     * @param uri
     * @param encode
     * @return
     */
    public static String encodeUri(String uri, String encode) {

        String encodedUri = uri;
        try {
            encodedUri = URLEncoder.encode(uri, encode);
        } catch (UnsupportedEncodingException ex) {
            encodedUri = uri;
        }
        return encodedUri;
    }

    /**
     * 解码
     * @param uri
     * @param encode
     * @return
     */
    public static String decodeUri(String uri, String encode) {

        String decodedUri = uri;
        try {
            decodedUri = URLDecoder.decode(uri, encode);
        } catch (UnsupportedEncodingException ex) {
            decodedUri = uri;
        }
        return decodedUri;
    }

    /**
     * 组装数据
     *
     * @param properties
     * @param encoding
     *            是否包含sign
     * @return
     */
    public static String getMapToString(Map<String, String> properties,
                                        String encoding) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(properties.keySet());
        // 按自然顺序升序排序
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = properties.get(key) + "";
            if(key.equals("sign")||key.equals("sign_type")){
                content.append((i == 0 ? "" : "&")+ key+ "="+  value );
                continue;
            }
            try {
                content.append((i == 0 ? "" : "&")+ key+ "="+ (StringUtils.isBlank(encoding) ? value : URLEncoder.encode(value, encoding)));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return content.toString();
    }

    public static void encodeMap(Map map,String enc){
        if(null!=map){
            Set<String> keySet=map.keySet();
            for (String  key : keySet) {
                if(key.equals("sign")||key.equals("sign_type")){
                    continue;
                }else{
                    try {
                        map.put(key, URLEncoder.encode(map.get(key)+"", enc));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
