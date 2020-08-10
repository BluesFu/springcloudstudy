package top.blues.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Siyang Fu
 * @Date: 8/8/20 12:15 AM
 */
@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id){
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_OK,id:"+id+"\t"+"O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")

    })
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber=5;
        try {
          TimeUnit.SECONDS.sleep(timeNumber);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return "线程池"+Thread.currentThread().getName()+"paymentInfo_TimeOut,id:"+id+"\t"+"O(∩_∩)O哈哈~耗时三秒钟";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_TimeOutHandler,id: "+id+"\t /(ㄒoㄒ)/~~";
    }
}
