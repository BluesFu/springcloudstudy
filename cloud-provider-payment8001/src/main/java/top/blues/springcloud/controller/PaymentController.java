package top.blues.springcloud.controller;




import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import top.blues.springcloud.entities.CommonResult;
import top.blues.springcloud.entities.Payment;
import top.blues.springcloud.service.PaymentService;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author fsy
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;


    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result =paymentService.create(payment);
        log.info("插入结果*******:"+result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功"+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> create(@PathVariable("id") Long id){
        Payment payment =paymentService.getPayment(id);
        log.info("查询结果*******:"+payment);
        if (payment!=null){
            return new CommonResult(200,"查询数据库成功"+serverPort,payment);
        }else{
            return new CommonResult(444,"查询数据库失败,id:"+id,null);
        }
    }
    @GetMapping(value = "/payment/discovery")
    public Object  discovery() {
        List<String> services=discoveryClient.getServices();
        for (String element: services
             ) {
            log.info("*****element"+element);
        }
        List<ServiceInstance> instances= discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance: instances
             ) {
            log.info(instance.getInstanceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }
}
