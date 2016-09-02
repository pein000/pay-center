package com.pein.alibaba.assemble;

import com.pein.alibaba.config.DirectConstants;
import com.pein.common.request.DirectPayRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiuwei on 2016/8/31.
 */
@Component
public class PayAssemble {

    /**
     * 即时到账组装数据
     * 按照规则
     * @param directPayRequest
     * @return
     */
    public Map<String, String> assemble(DirectPayRequest directPayRequest) {
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", DirectConstants.DIRECT_PAY_SERVICE);
        sParaTemp.put("partner", directPayRequest.getSeller_id());
        sParaTemp.put("seller_id", directPayRequest.getSeller_id());
        sParaTemp.put("_input_charset", DirectConstants.CHASET_UTF8);
        sParaTemp.put("payment_type", directPayRequest.getPayment_type());
        sParaTemp.put("notify_url", directPayRequest.getNotify_url());
        sParaTemp.put("return_url", directPayRequest.getReturn_url());
        sParaTemp.put("out_trade_no", directPayRequest.getOut_trade_no());
        sParaTemp.put("subject", directPayRequest.getSubject());
        sParaTemp.put("total_fee", directPayRequest.getTotal_fee());
        sParaTemp.put("body", directPayRequest.getBody());
        sParaTemp.put("qr_pay_mode", DirectConstants.DIRECT_PAY_MODE_2);
        return sParaTemp;
    }
}
