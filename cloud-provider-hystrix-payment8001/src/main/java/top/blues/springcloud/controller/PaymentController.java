package top.blues.springcloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.blues.springcloud.service.PaymentService;

import javax.annotation.Resource;

/**
 * @Author: Siyang Fu
 * @Date: 8/8/20 12:22 AM
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("$service.prot")
    private String servicePort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
       String result=paymentService.paymentInfo_OK(id);
       log.info("*****result"+result);
       return result;
    }
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id")Integer id){
        String result=paymentService.paymentInfo_TimeOut(id);
        log.info(">>>>>result"+result);
        return result;
    }
}
