package com.pein.alibaba.controller;

import com.pein.alibaba.analyse.DirectPayAnalysor;
import com.pein.alibaba.assemble.PayAssemble;
import com.pein.alibaba.utils.AlipayNotify;
import com.pein.alibaba.utils.AlipaySubmit;
import com.pein.common.enums.PayResponseStatus;
import com.pein.common.request.alibaba.DirectPayRequest;
import com.pein.common.response.PayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by qiuwei on 2016/8/31.
 * 即时到账
 */
@RestController
@RequestMapping
public class DirectPayFromController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private PayAssemble payAssembor;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("direct_pay")
    public PayResponse pay(@RequestBody DirectPayRequest directPayRequest) {
        LOGGER.info("directPayRequest : {}", directPayRequest);

        //建立请求
        String response = AlipaySubmit.buildRequest(payAssembor.assemble(directPayRequest),"get","确认");
        LOGGER.info("directPay response : {}.",response);
        return DirectPayAnalysor.analyse(directPayRequest, response);
    }

    /**
     * 及时到账异步通知
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("notify")
    public PayResponse notify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
        //请求商户，更加交易订单号，查询商户的partner。
//        String partner = restTemplate.postForObject();
        //TODO
        String partner = restTemplate.postForObject(null, null, null);
        params.put("partner", partner);

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

        if(AlipayNotify.verify(params)){//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码
            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
                return new PayResponse(out_trade_no, trade_no, PayResponseStatus.SUCCESS.getStatus());
            }
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            response.getOutputStream().print("success");	//请不要修改或删除
            //////////////////////////////////////////////////////////////////////////////////////////
        }else{//验证失败
            response.getOutputStream().print("fail");
        }
        return new PayResponse(out_trade_no, trade_no, PayResponseStatus.FAIL.getStatus());
    }
}
