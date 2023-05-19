package com.jolly.userservice.service.rest;

import com.jolly.userservice.config.CustomFeignConfig;
import com.jolly.userservice.model.rest.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jolly
 */
// the api url and host can be omitted since the service registry
// will be able to identify based on the service name
// in this case: core-banking-service
@FeignClient(name = "core-banking-service", configuration = CustomFeignConfig.class)
public interface BankingCoreRestClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/user/{identification}")
    UserResponse readUser(@PathVariable("identification") String identification);
}
