package com.semihbkgr.ecommerce.uiserver.client;

import com.semihbkgr.ecommerce.modelcommon.user.User;
import com.semihbkgr.ecommerce.modelcommon.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient("USER")
public interface UserClient {

    @GetMapping("/get/{username}")
    User getByUsername(@PathVariable("username") String username, @RequestHeader("Authorization") String authorization);

    @GetMapping("/search/{username}")
    List<UserInfo> searchByUsername(@PathVariable("username") String username, @RequestHeader("Authorization") String authorization);

}
