package top.blues.springcloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.blues.springcloud.entities.CommonResult;
import top.blues.springcloud.entities.Payment;
import top.blues.springcloud.lb.LoadBalancer;


import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author fsy
 */
@RestController
@Slf4j
public class OrderController {
   // private static final String PAYMENT_URL="http://localhost:8001";

    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    private static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances.isEmpty() || instances.size() <= 0){
            return null;
        }
            ServiceInstance serviceInstance=loadBalancer.instance(instances);
            URI uri=serviceInstance.getUri();

            return restTemplate.getForObject(uri+"/payment/lb",String.class);

    }
}
