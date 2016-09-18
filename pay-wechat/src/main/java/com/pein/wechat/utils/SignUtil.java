package com.pein.wechat.utils;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

/**
 * 验签和签名工具类
 * @author lujiahua_yf
 *
 */
public class SignUtil{

    /**
     * 签名算法
     * @param map 要参与签名的数据对象
     * @return 签名
     */
    public static String getSign(Map<String,Object> map,String apiKey){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(entry.getValue() != null && !entry.getValue().equals("") ){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + apiKey;
        result = MD5.MD5Encode(result).toUpperCase();

        return result;
    }
    
    /**
     * 签名算法
     * @param map 要参与签名的数据对象
     * @return 签名
     */
    public static String getSignApp(Map<String,Object> map, Logger log){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(entry.getValue()!=""){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        //result += "key=" + Configure.getKey();
        boolean isNeedLog = (log!=null && log.isDebugEnabled());
        if( isNeedLog ){
            log.debug("Sign Before MD5:["+ result +"]");
        }
        result = MD5.MD5Encode(result).toUpperCase();
        if( isNeedLog ){
            //log.debug("Sign Result:" + result);
        }
        return result;
    }
    
    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
	
	
}
