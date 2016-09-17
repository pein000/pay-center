package com.pein.alibaba.assemble;

import com.pein.alibaba.config.DirectConstants;
import com.pein.alibaba.key.DirectPayRequestKey;
import com.pein.common.request.alibaba.DirectPayRequest;
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
        sParaTemp.put(DirectPayRequestKey.SERVICE, DirectConstants.DIRECT_PAY_SERVICE);
        sParaTemp.put(DirectPayRequestKey.PARTNER, directPayRequest.getPartner());
        sParaTemp.put(DirectPayRequestKey.SELLER_ID, directPayRequest.getSeller_id());
        sParaTemp.put(DirectPayRequestKey.INPUT_CHARSET, DirectConstants.CHASET_UTF8);
        sParaTemp.put(DirectPayRequestKey.PAYMENT_TYPE, DirectConstants.PAYMENT_TYPE);
        sParaTemp.put(DirectPayRequestKey.NOTIFY_URL, directPayRequest.getNotify_url());
        sParaTemp.put(DirectPayRequestKey.RETURN_URL, directPayRequest.getReturn_url());
        sParaTemp.put(DirectPayRequestKey.OUT_TRADE_NO, directPayRequest.getOut_trade_no());
        sParaTemp.put(DirectPayRequestKey.SUBJECT, directPayRequest.getSubject());
        sParaTemp.put(DirectPayRequestKey.TOTAL_FEE, directPayRequest.getTotal_fee());
        sParaTemp.put(DirectPayRequestKey.BODY, directPayRequest.getBody());
        sParaTemp.put(DirectPayRequestKey.QR_PAY_MODE, DirectConstants.DIRECT_PAY_MODE_2);
        sParaTemp.put(DirectPayRequestKey.SIGN,directPayRequest.getSign());
        sParaTemp.put(DirectPayRequestKey.SIGN_TYPE, directPayRequest.getSign_type());
        return sParaTemp;
    }
}
