package com.pein.alibaba.utils2;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.2
 *日期：2011-03-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

		//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
		//合作身份者id，验签需要
//		public static final String partner = "2088311111433334";
	
	    public static final String partner = "2088201564809153";
	
		
		// 交易安全检验码，由数字和字母组成的32位字符串
		public static String key = "umz4aea6g97skeect0jtxigvjkrimd0o";
	
	
		// 调试用，创建TXT日志路径
		public static String log_path = "D:\\alipay_log_" + System.currentTimeMillis()+".txt";
	
	
		//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

		//商户私钥，自助生成
//		public static final String PRIVATE ="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAN7e9iSiVwRkXM6lqRQD9EzIc3nle6EUdHAE8dFzpI/S4LfHqzUlQTf5LwVmhGwoMTV1Y2d7KwvjsyDjtf1FJtswUsLLbAMgikGzmnS9AMCYmHdNl5aDSOs9r07I4IEVdiMY0fkI4Drs7sp46FVBvumQyqvKiW/CFOEQaDiuYvnbAgMBAAECgYBYuVYwG1ic7P9tXNHEoBZfguVFv6/J3oTEG5PRGEKI5yCCyUBFNgGSsOahNcC99foxF/xss8HdR9aF32ER5LekiJUMRfe69Mx+eN4NFBn8DpvGIyaQkAE4dgAoE/wYQc8RAhnVT3cSs0FHrex91vZgUl8mKNr2upBqSyL7gvB7YQJBAPFJ1XQQCOXdiDOr6Ttwm/7Ul49SEJTbiEgdR3/IA2I1orv+aC7RUFAcD1zoMZXSImb8eAEw7Zdz3gSC++Zlc7MCQQDsdahK7+CYNbrDD8oHIsFQqn4LRU9jvnNGVprmEcHqXSUeDzXILTwH6eDk5MQCEuVCf4XgD0tDG+QuhRxh0205AkEA0vCnaTXzcXGAdxvdZJeH6PpWHXezA1ABDnp6XV2wh5U4VX2N+xAGVGKTk7dyUfZJGRuylef+bh9fgzY3ni4nPQJBAKt/Oiv/hnlenrHSxcgfIO4MeZZbEpVZD+o37ehngKkmHUGL7oncpOloa0AX20FVywlLzCERdpiZpglHaapZ2aECQQDkSElb1oWTGHp3OgSaNETNd8A9NnlYph1vrI1Hy/Lki5q8OkPunBxZ0JqT43DAWFzv/+RS7Dt/yVimb7TB0vZA";
		
		public static final String PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	    // 签名方式 md5
        public static String SIGN_MD5 = "MD5";
	
	    // 签名方式 rsa
	    public static String SIGN_RSA = "RSA";

	    public static String CHARSET = "UTF-8";

}
