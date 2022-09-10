package cn.xiit.springcloud;

import cn.xiit.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@RestController
public class DeptConsumerController {

    //RestTemplate 请求
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

//    private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX = "SPRINGCLOUD-PROVIDER-DEPT";

    @RequestMapping("comsumer/dept/get/{id}")
    public Dept get(@PathVariable("id") int id)
    {
        return restTemplate.getForObject(getUrl(REST_URL_PREFIX)+"/dept/get/"+id, Dept.class);
    }

    @RequestMapping("/comsumer/dept/add")
    public Boolean add(Dept dept){
        System.out.println(dept);
        return restTemplate.postForObject(getUrl(REST_URL_PREFIX)+"/dept/add", dept, Boolean.class);
    }

    @RequestMapping("/comsumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(getUrl(REST_URL_PREFIX)+"/dept/list", List.class);
    }


    /**
     * 获取指定url
     * @param clientApplicationName 指定的服务提供名
     * @return
     */
    private String getUrl(String clientApplicationName) {
        // 使用loadBalancerClient的choose函数来负载均衡的选出一个eurekaClient的服务实例
        ServiceInstance serviceInstance = loadBalancerClient.choose(clientApplicationName);
        // 获取之前eurekaClient /all接口地址
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
        System.out.println(url);
        return url;
    }

}
