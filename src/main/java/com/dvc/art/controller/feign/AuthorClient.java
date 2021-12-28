package com.dvc.art.controller.feign;

import com.dvc.art.model.jpa.Author;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="author-service")
public interface AuthorClient {

    @GetMapping("/authors/{id}")
    Author findById(@PathVariable("id") long id);
}
