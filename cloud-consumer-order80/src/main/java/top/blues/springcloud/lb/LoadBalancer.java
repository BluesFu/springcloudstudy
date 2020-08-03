package top.blues.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author kubuntu
 */
public interface LoadBalancer {
    ServiceInstance instance (List<ServiceInstance> serviceInstances);

}
