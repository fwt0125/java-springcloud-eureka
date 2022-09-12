package cn.xiit.springcloud.Controllor;

import cn.xiit.springcloud.pojo.Dept;
import cn.xiit.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    //    @HystrixCommand(fallbackMethod = "hystrixGet")
    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        Dept dept = deptService.queryById(id);

        if(dept == null){
            throw new RuntimeException("id=>"+id+"不存在");
        }

        return dept;
    }

    public Dept hystrixGet(@PathVariable("id") Long id){
        return new Dept().setDeptno(id).setDname("hystrix: id=>"+id+"不存在");
    }
}
