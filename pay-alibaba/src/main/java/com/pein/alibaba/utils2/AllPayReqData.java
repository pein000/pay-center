package com.pein.alibaba.utils2;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 下单接口
 * @author win
 *
 */
public class AllPayReqData {

	private String payment_type = ""; // 支付类型
	private String out_trade_no = ""; // 订单编号
	private String subject = ""; // 科目
	private String body =""; // 主体
	private String total_fee = ""; // 订单总金额
	private String show_url = ""; // 商品展示地址
	private String partner = ""; // 合作商ID
	private String return_url = ""; // 返回地址
    private String notify_url = ""; // 通知地址
    private String seller_email = ""; // 卖方email
    private String _input_charset = ""; // 输入参数
    private String service = ""; // 服务
    private String paymethod = ""; // 支付方式
    private String defaultbank = ""; // 默认银行
    private String seller_id; // 卖方id
    private String qr_pay_mode; // 扫码支付
    
    

	public AllPayReqData(String out_trade_no, String total_fee) {
		setTotal_fee(total_fee);
		setOut_trade_no(out_trade_no);
		setPayment_type(Configure.getPayment_type());
		setSubject(Configure.getTRADE_SUBJECT());
		setBody(Configure.getTRADE_BODY());
		setShow_url(Configure.getShow_url());
		setPartner(Configure.getAliPaySellerAccount());
		setReturn_url(Configure.getReturn_url());
		setNotify_url(Configure.getNotify_url());
		setSeller_email(Configure.getSellerEmail());
		set_input_charset(Configure.getInputCharset());
		setService(Configure.getService_pay());
		setPaymethod(Configure.getPaymethod());
		setDefaultbank(null);
		setSeller_id(Configure.getAliPaySellerAccount());
		setQr_pay_mode(Configure.getQr_pay_mode()); 
	}

	/**
     * 对象转map
     * @return
     */
    public Map<String,Object> toMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            String obj;
            try {
                obj = (String)field.get(this);
                if(obj!=null){
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getShow_url() {
		return show_url;
	}

	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String get_input_charset() {
		return _input_charset;
	}

	public void set_input_charset(String _input_charset) {
		this._input_charset = _input_charset;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public String getDefaultbank() {
		return defaultbank;
	}

	public void setDefaultbank(String defaultbank) {
		this.defaultbank = defaultbank;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getQr_pay_mode() {
		return qr_pay_mode;
	}

	public void setQr_pay_mode(String qr_pay_mode) {
		this.qr_pay_mode = qr_pay_mode;
	}
    
}
