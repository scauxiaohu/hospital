package com.hospital.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "alipay")
@Data
public class AliPayConfig {

    // 应用ID
    private String APP_ID;
    // 商户私钥
    private String APP_PRIVATE_KEY;
    // 编码格式
    private String CHARSET;
    // 支付宝公钥
    private String ALIPAY_PUBLIC_KEY;
    // 这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    private String GATEWAY_URL;
    private String FORMAT;
    // 签名方式
    private String SIGN_TYPE;
    // 支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    private String NOTIFY_URL;
    // 支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    private String RETURN_URL;

    @Bean
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(GATEWAY_URL, APP_ID,
                APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY,
                SIGN_TYPE);
    }
}