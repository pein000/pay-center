package com.pein.alibaba.utils2;

import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;



/* *
 *类名：AlipayFunction
 *功能：支付宝接口公用函数类
 *详细：该类是请求、通知返回两个文件所调用的公用函数核心处理文件，不需要修改
 *版本：3.2
 *日期：2011-03-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayCore {

	
    /**
     * 生成签名结果
     * @param sArray 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildMysign(Map<String, Object> sArray,String prikeyMD5) {
        String prestr = createLinkString(sArray); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        System.out.println("ALIPAYFundChannelKey.MD5_key=="+prikeyMD5);
        //解码异步回调uri
        String realUri = decodeUri(prestr,"utf-8");
        String mysign = AlipayMd5Encrypt.sign( realUri,prikeyMD5,"utf-8");
        return mysign;
    }
    
    
    /**
     * 生成签名结果
     * @param sArray 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildMysign_RSA(Map<String, Object> sArray,String prikey,String encode) {
        String prestr = createLinkString(sArray); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        System.out.println("ALIPAYFundChannelKey.RSA_key=="+prikey);
        
        //解码异步回调uri
//        String realUri = decodeUri(prestr,encode);
        
        String mysign = AlipayRsaEncrypt.sign(prestr,prikey);
        
        String encodeUrl = AlipayCoreUtil.encodeUri(mysign, encode);
        
        return encodeUrl;
    }
    
//    /**
//     * 生成签名结果
//     * @param sArray 要签名的数组
//     * @return 签名结果字符串
//     */
//    public static String buildMysign_RSA(Map<String, String> sArray,String encode) {
//        String prestr = createLinkString(sArray); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
//        System.out.println("ALIPAYFundChannelKey.RSA_key=="+private_key);
//        System.out.println("prestr:"+prestr);
//
//        String mysign = AlipayRsaEncrypt.sign(prestr,private_key);
//        
//        String encodeUrl = AlipayCoreUtil.encodeUri(mysign, encode);
//
//        return encodeUrl;
//    }
    
    /**
     * 生成签名结果
     * @param signStr 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildMysign_RSA4SDK(String signStr,Properties properties,String encode) {
        System.out.println("ALIPAYFundChannelKey.RSA_key=="+properties.getProperty("RSA_private_key"));

        //解码异步回调uri
        String realUri = decodeUri(signStr,encode);
        
        String mysign = AlipayRsaEncrypt.sign( realUri,properties.getProperty("RSA_private_key"));
        return mysign;
    }
    
    /**
     * 解码
     * @param uri
     * @param encode
     * @return
     */
    public static String decodeUri(String uri,String encode){
        
        String decodedUri = uri;
        try{
            decodedUri = URLDecoder.decode(uri,encode);
        }catch(UnsupportedEncodingException ex){
            decodedUri = uri;
        }
        return decodedUri;
    }
    
    /**
     * <p>生成签名结果</p>
     * @param sArray
     * @param properties
     * @param encode
     * @return
     */
    public static String buildNoSorktMysign(Map<String, String> sArray,Properties properties,String encode) {
        
        String prestr = createLinkStringNoSort(sArray); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        System.out.println("ALIPAYFundChannelKey.MD5_key=="+properties.getProperty("MD5_key"));
        String mysign = AlipayMd5Encrypt.sign(prestr,properties.getProperty("MD5_key"),encode);
        return mysign;
    }

    /**
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, Object> paraFilter(Map<String, Object> sArray) {

        Map<String, Object> result = new HashMap<String, Object>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = (String) sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /**
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilterStr(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = (String) sArray.get(key);
            if (value == null || value.equals("null") || value.equals("") || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkStr(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value =  params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createAlipayLinkString(Map<String, Object> params, String inputCharset) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = AlipayCore.decodeUri((String)params.get(key),inputCharset);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
    
    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, Object> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = (String) params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
    
    /**
     * 支付宝手机网页端 签名组合参数规则
     * @param params
     * @return
     */
    public static String createLinkStringNoSort(Map<String, String> params) {
         String prestr = "";
         for (String key : params.keySet()) {
             prestr = prestr + key + "=" + params.get(key) + "&";
         }
         prestr = prestr.substring(0,prestr.length()-1);
         
         return prestr;
    }

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(AlipayConfig.log_path);
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 转换网页请求参数 
     * @param request
     * @return
     */
    public static Map<String,String> pageParamsToMap(String request){
        
            Map<String, String> map = new HashMap<String, String>();
            //根据空格把源字符串划分成数组
            if(StringUtils.isNotBlank(request) || request.contains("&")){
                String[] arr = request.split("&");
                for (int i = 0; i < arr.length; i++) {
                   //根据=把数组元素再分割成左右两个元素
                    if(arr[i].contains("=")){
                        String[] tmp = arr[i].split("=");
                        map.put(tmp[0], tmp[1]);
                    }
                }
            }
            return map;
     }
}
