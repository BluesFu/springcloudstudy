package top.blues.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.blues.springcloud.entities.CommonResult;
import top.blues.springcloud.entities.Payment;

/**
 * @author kubuntu
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    /** 通过id查找payment对象
     * @param id
     * @return Payment
     */
    @GetMapping(value = "/payment/get/{id}")
     CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);


    /**feign超时报错测试
     * @return String
     */
    @GetMapping("/payment/feign/timeout")
     String paymentFeignTimeOut();

}
