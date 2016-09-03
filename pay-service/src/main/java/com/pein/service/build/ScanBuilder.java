package com.pein.service.build;

import com.pein.common.enums.ChannelCode;
import com.pein.common.enums.OperateStyle;
import com.pein.common.enums.TradeStatus;
import com.pein.common.request.CenterScanPayRequest;
import com.pein.common.request.DirectPayRequest;
import com.pein.repository.entity.OperateFlow;
import com.pein.repository.entity.TraderFlow;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

/**
 * Created by qiuwei on 2016/9/1.
 */
public class ScanBuilder {


    public static TraderFlow buildScanPayQueryTradeFlow(CenterScanPayRequest centerScanPayRequest) {
        TraderFlow traderFlow = new TraderFlow();
        traderFlow.setAppKey(centerScanPayRequest.getAppKey());
        traderFlow.setInstTradeNo(centerScanPayRequest.getInstOrderNo());
        return traderFlow;
    }

    public static TraderFlow buildScanPayInsertTradeFlow(CenterScanPayRequest centerScanPayRequest,String channelUrl) {
        TraderFlow traderFlow = new TraderFlow();
        BeanUtils.copyProperties(centerScanPayRequest,traderFlow);
        traderFlow.setId(UUID.randomUUID().toString().replace("-",""));
        traderFlow.setChannelCode(ChannelCode.matchValue(centerScanPayRequest.getChannelCode()));
        traderFlow.setTradeStates(TradeStatus.WAITPAY.getStatus());
        traderFlow.setChannelUrl(channelUrl);
        return traderFlow;
    }

    public static OperateFlow buildScanPayInsertTradeFlow(String tradeId) {
        OperateFlow operateFlow = new OperateFlow();
        operateFlow.setId(UUID.randomUUID().toString().replace("-",""));
        operateFlow.setTradeId(tradeId);
        operateFlow.setOperateStatus(TradeStatus.WAITPAY.getStatus());
        operateFlow.setOperateStyle(OperateStyle.CUSTOMER.getValue());
        return operateFlow;
    }
    public static DirectPayRequest buildScanPayDirectPayRequest(CenterScanPayRequest centerScanPayRequest,String notifyUrl) {
        DirectPayRequest directPayRequest = new DirectPayRequest();
        directPayRequest.setAppKey(centerScanPayRequest.getAppKey());
        directPayRequest.setSubject(centerScanPayRequest.getProSubject());
        directPayRequest.setBody(centerScanPayRequest.getProDesc());
        directPayRequest.setTotal_fee(centerScanPayRequest.getAmount());
        directPayRequest.setOut_trade_no(centerScanPayRequest.getInstOrderNo());
        directPayRequest.setNotify_url(notifyUrl);
        return directPayRequest;
    }
}
