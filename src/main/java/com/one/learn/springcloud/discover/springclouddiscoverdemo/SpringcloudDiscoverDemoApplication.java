package com.one.learn.springcloud.discover.springclouddiscoverdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SpringcloudDiscoverDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudDiscoverDemoApplication.class, args);
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("services")
    public List<String> services() {
        return discoveryClient.getServices();
    }

    @GetMapping("services/{id}")
    public List<ServiceInstance> instance(@PathVariable("id") String id) {
        return discoveryClient.getInstances(id);
    }

    @GetMapping("services/{id}/{iid}")
    public ServiceInstance services(@PathVariable("id") String id, @PathVariable("iid") String iid) {
        return discoveryClient.getInstances(id).stream().filter(e -> e.getInstanceId().equals(iid)).findFirst().orElse(null);
    }
}
