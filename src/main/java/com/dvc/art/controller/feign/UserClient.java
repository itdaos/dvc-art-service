package com.dvc.art.controller.feign;

import com.dvc.art.model.jpa.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="identity-service")
public interface UserClient {

    @GetMapping("/test/get/{id}")
    User findById(@PathVariable("id") long id);
}
