package top.blues.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.blues.springcloud.entities.CommonResult;
import top.blues.springcloud.entities.Payment;
import top.blues.springcloud.service.PaymentFeignService;

import javax.annotation.Resource;

/**
 * @author kubuntu
 */
@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    CommonResult<Payment> create(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    String paymentFeignTimeOut(){
        //feign-ribbon默认等待1s
        return paymentFeignService.paymentFeignTimeOut();
    }

}
