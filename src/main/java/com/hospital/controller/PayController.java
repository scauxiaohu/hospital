package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.hospital.config.AliPayConfig;
import com.hospital.entity.Orders;
import com.hospital.entity.Payment;
import com.hospital.mapper.OrdersMapper;
import com.hospital.mapper.PaymentMapper;
import com.hospital.service.PaymentService;
import com.hospital.service.impl.PaymentServiceImpl;
import com.hospital.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.hospital.util.Status.ALIPAY_ORDER_CREATE_FAILED;
import static com.hospital.util.Status.ALIPAY_PAY_FAILED;

@RestController
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private AliPayConfig aliPayConfig;
    @Autowired
    private PaymentService paymentService;
    @GetMapping ("alipay")
    public void alipay(Integer orderId, Double price,HttpServletResponse response) throws
            IOException {
//        String currentUserId = BaseContext.getCurrentUserId();
        // 实例化客户端,填入所需参数
      /*  AlipayTradePagePayRequest request = new
                AlipayTradePagePayRequest();*/
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();

        // 在公共参数中设置回跳和通知地址
        request.setReturnUrl(aliPayConfig.getRETURN_URL());
        request.setNotifyUrl(aliPayConfig.getNOTIFY_URL());
        // 根据订单编号,查询订单相关信息

        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = orderId.toString();
        // 付款金额，必填
        // String total_amount = order.getSetmeal().getPrice().toString();
        String total_amount = price.toString();
        // 订单名称，必填
        String subject = "熙心健康体检";
        // 商品描述，可空
      /*  String body = order.getSetmeal().getName();*/
        request.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + "预约费用" + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";

        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
            String trade_no = request.getBizContent().split("\"")[3];
            //获得这个订单的支付宝交易号

        } catch (AlipayApiException e) {
            log.error("支付宝调用异常"
                    , e);
        }
        response.setContentType("text/html;charset=" +
                aliPayConfig.getCHARSET());
        response.getWriter().write(form);// 直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

    @GetMapping(value = "/returnUrl")
    public void returnUrl(HttpServletRequest request, HttpServletResponse
            response)
            throws IOException, AlipayApiException {
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr +
                        values[i] + ",";
            }
            // 解决乱码
            valueStr = new String(valueStr.getBytes(StandardCharsets.UTF_8),
                    StandardCharsets.UTF_8);
            params.put(name, valueStr);
        }

        System.out.println(params);// 查看参数都有哪些
        boolean signVerified = AlipaySignature.rsaCheckV1(params,
                aliPayConfig.getALIPAY_PUBLIC_KEY(), aliPayConfig.getCHARSET(),
                aliPayConfig.getSIGN_TYPE()); // 调用SDK验证签名
        // 验证签名通过
        if(signVerified){
            // 商户订单号
            String out_trade_no = new
                    String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            // 支付宝交易号
            String trade_no = new
                    String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            // 付款金额
            String total_amount = new
                    String(request.getParameter("total_amount").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            System.out.println("商户订单号 = " + out_trade_no);
            System.out.println("支付宝交易号 = " + trade_no);
            System.out.println("付款金额 = " + total_amount);

            // 支付成功，修改支付状态
            Orders orders = new Orders();
            orders.setOrderId(Integer.valueOf(out_trade_no));
            orders.setState(1);
            ordersMapper.update(orders);

            Payment payment=new Payment();
            payment.setOdId(Integer.valueOf(out_trade_no));
            payment.setPyId(trade_no);
            payment.setPrice(new BigDecimal(total_amount));
            payment.setState(1);
            paymentService.insert(payment);

            response.sendRedirect("http://localhost:5173/appointmentsuccess");
        }else{
            response.sendRedirect("http://localhost:5173/appointmentfail");
        }
    }
}