package top.blues.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @Author: Siyang Fu
 * @Date: 8/10/20 10:23 PM
 */

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    /** ok
     * @param id
     * @return String
     */
    @GetMapping("/payment/hystrix/ok/{id}")
     String paymentInfo_OK(@PathVariable("id") Integer id);

    /** timeout
     * @param id
     * @return String
     */
    @GetMapping("/payment/hystrix/timeout/{id}")
     String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
