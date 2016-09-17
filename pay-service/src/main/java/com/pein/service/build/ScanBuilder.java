package com.pein.service.build;

import com.pein.common.enums.ChannelCode;
import com.pein.common.enums.OperateStyle;
import com.pein.common.enums.SignType;
import com.pein.common.enums.TradeStatus;
import com.pein.common.request.CenterScanPayRequest;
import com.pein.common.request.alibaba.DirectPayRequest;
import com.pein.repository.entity.MerchantChannelAli;
import com.pein.repository.entity.OperateFlow;
import com.pein.repository.entity.TraderFlow;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

/**
 * Created by qiuwei on 2016/9/1.
 */
public class ScanBuilder {

    public static MerchantChannelAli buildScanPaySelectMerchantChannel(CenterScanPayRequest centerScanPayRequest) {
        MerchantChannelAli merchantChannelAli = new MerchantChannelAli();
        merchantChannelAli.setAppKey(centerScanPayRequest.getAppKey());
        merchantChannelAli.setCode(centerScanPayRequest.getChannelCode());
        return merchantChannelAli;
    }

    public static TraderFlow buildScanPayQueryTradeFlow(CenterScanPayRequest centerScanPayRequest) {
        TraderFlow traderFlow = new TraderFlow();
        traderFlow.setAppKey(centerScanPayRequest.getAppKey());
        traderFlow.setInstTradeNo(centerScanPayRequest.getInstOrderNo());
        return traderFlow;
    }

    public static CenterScanPayRequest buildScanPaynetWorkRequest(CenterScanPayRequest centerScanPayRequest, MerchantChannelAli merchantChannelAli) {
        centerScanPayRequest.setpId(merchantChannelAli.getpId());
        centerScanPayRequest.setPriKey(merchantChannelAli.getRsaPri());
        centerScanPayRequest.setPubKey(merchantChannelAli.getRsaPub());
        centerScanPayRequest.setMd5(merchantChannelAli.getMd5());
        return centerScanPayRequest;
    }

    public static TraderFlow buildScanPayInsertTradeFlow(CenterScanPayRequest centerScanPayRequest,String channelUrl) {
        TraderFlow traderFlow = new TraderFlow();
        BeanUtils.copyProperties(centerScanPayRequest,traderFlow);
        traderFlow.setId(UUID.randomUUID().toString().replace("-",""));
        traderFlow.setChannelCode(ChannelCode.matchCode(centerScanPayRequest.getChannelCode()));
        traderFlow.setTradeStates(TradeStatus.WAITPAY.getStatus());
        traderFlow.setChannelUrl(channelUrl);
        traderFlow.setInstTradeNo(centerScanPayRequest.getInstOrderNo());
        return traderFlow;
    }

    public static TraderFlow buildScanPayCancelTradeFlowAllChannel(CenterScanPayRequest centerScanPayRequest) {
        TraderFlow traderFlow = new TraderFlow();
        traderFlow.setAppKey(centerScanPayRequest.getAppKey());
        traderFlow.setInstTradeNo(centerScanPayRequest.getInstOrderNo());
        traderFlow.setTradeStates(TradeStatus.CANCELED.getStatus());
        return traderFlow;
    }

    public static TraderFlow buildScanPayCancelTradeFlow(CenterScanPayRequest centerScanPayRequest) {
        TraderFlow traderFlow = new TraderFlow();
        traderFlow.setAppKey(centerScanPayRequest.getAppKey());
        traderFlow.setInstTradeNo(centerScanPayRequest.getInstOrderNo());
        traderFlow.setChannelCode(centerScanPayRequest.getChannelCode());
        traderFlow.setTradeStates(TradeStatus.CANCELED.getStatus());
        return traderFlow;
    }


    public static TraderFlow buildScanPayWaitTradeFlow(CenterScanPayRequest centerScanPayRequest) {
        TraderFlow traderFlow = new TraderFlow();
        traderFlow.setAppKey(centerScanPayRequest.getAppKey());
        traderFlow.setInstTradeNo(centerScanPayRequest.getInstOrderNo());
        traderFlow.setChannelCode(centerScanPayRequest.getChannelCode());
        traderFlow.setTradeStates(TradeStatus.WAITPAY.getStatus());
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
        directPayRequest.setPartner(centerScanPayRequest.getpId());
        directPayRequest.setOut_trade_no(centerScanPayRequest.getInstOrderNo());
        directPayRequest.setSeller_id(centerScanPayRequest.getpId());
//        directPayRequest.setSign_type(SignType.RSA.getType());
//        directPayRequest.setSign(centerScanPayRequest.getPriKey());
        directPayRequest.setSign_type(SignType.MD5.getType());
        directPayRequest.setSign(centerScanPayRequest.getMd5());
        return directPayRequest;
    }
}
