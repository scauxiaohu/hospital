package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.hospital.config.AliPayConfig;
import com.hospital.entity.Orders;
import com.hospital.entity.Payment;
import com.hospital.entity.Users;
import com.hospital.mapper.OrdersMapper;
import com.hospital.mapper.PaymentMapper;
import com.hospital.service.OrdersService;
import com.hospital.service.PaymentService;
import com.hospital.service.impl.PaymentServiceImpl;
import com.hospital.util.Result;
import com.hospital.util.SendSms;
import com.hospital.util.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    private OrdersService ordersService;
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
@Resource
private SendSms sendSms;

    @GetMapping(value = "/returnUrl")
    public void returnUrl(HttpServletRequest request, HttpServletResponse
            response)
            throws Exception {
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
            ordersService.update(orders);

            Result result=ordersService.queryById(Integer.valueOf(out_trade_no));

            Payment payment=new Payment();
            payment.setOdId(Integer.valueOf(out_trade_no));
            payment.setPyId(trade_no);
            payment.setPrice(new BigDecimal(total_amount));
            payment.setState(1);
            paymentService.insert(payment);
            //工具类，将json字符串转Users对象
            Orders orders1 = (Orders)result.getData();
            // 发送短信通知
            sendSms.sendSms(orders1.getUserId(),3);
            response.sendRedirect("http://localhost:5173/appointmentsuccess");
        }else{
            response.sendRedirect("http://localhost:5173/appointmentfail");
        }
    }

    @GetMapping("refund")

    public Result refund(Integer orderId) throws
            IOException, AlipayApiException {
        Payment payment = paymentService.selectByOrderId(orderId);

        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(String.valueOf(orderId));
        model.setRefundAmount(String.valueOf(payment.getPrice()));
        request.setBizModel(model);
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        if (response.isSuccess()) {
            System.out.println("调用成功");
            // 退款成功，修改订单状态
            Orders orders = new Orders();
            orders.setOrderId(orderId);
            orders.setState(0);
            ordersService.update(orders);
            return Result.success("退款成功");
        } else {
            System.out.println("调用失败");
            // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
            // String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
            // System.out.println(diagnosisUrl);

            Orders orders = new Orders();
            orders.setOrderId(orderId);
            orders.setState(1);
            ordersService.update(orders);
            return Result.error("退款失败");
        }
    }
}