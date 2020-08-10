package top.blues.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.blues.springcloud.service.PaymentHystrixService;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * The type Order hystrix controller.
 *
 * @Author: Siyang Fu
 * @Date: 8 /10/20 10:31 PM
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    /**
     * Payment info ok string.
     *
     * @param id the id
     * @return the string
     */
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_OK(id);
    }


    /**
     * Payment info time out string.
     *
     * @param id the id
     * @return the string
     */
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
    })
    //@HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
    {
        int timeNumber=5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    /**
     * Payment time out fallback method string.
     *
     * @param id the id
     * @return the string
     */
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id)
    {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }


    /**
     * 下面是全局fallback方法
     *
     * @return string
     */
    public String payment_Global_FallbackMethod()
    {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
