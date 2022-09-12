package cn.xiit.springcloud.controller;

import cn.xiit.springcloud.pojo.Dept;
import cn.xiit.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private DeptClientService service = null;

    @RequestMapping("comsumer/dept/get/{id}")
    public Dept get(@PathVariable("id") int id)
    {
        return this.service.queryById(id);
    }

    @RequestMapping("/comsumer/dept/add")
    public Boolean add(Dept dept){
        return this.service.addDept(dept);
    }

    @RequestMapping("/comsumer/dept/list")
    public List<Dept> list(){
        return this.service.queryAll();
    }

}
