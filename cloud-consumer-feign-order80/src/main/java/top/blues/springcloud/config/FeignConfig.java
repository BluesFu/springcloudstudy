package top.blues.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author kubuntu
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLevel(){
        return Logger.Level.FULL;
    }

}
