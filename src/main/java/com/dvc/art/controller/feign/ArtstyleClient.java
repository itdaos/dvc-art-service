package com.dvc.art.controller.feign;

import com.dvc.art.model.jpa.Artstyle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="artstyle-service")
public interface ArtstyleClient {

    @GetMapping("/artstyles/{id}")
    Artstyle findById(@PathVariable("id") long id);
}
