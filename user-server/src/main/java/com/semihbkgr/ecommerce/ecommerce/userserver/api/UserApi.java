package com.semihbkgr.ecommerce.ecommerce.userserver.api;

import com.semihbkgr.ecommerce.ecommerce.userserver.service.UserService;
import com.semihbkgr.ecommerce.ecommerce.userserver.util.Page;
import com.semihbkgr.ecommerce.modelcommon.user.User;
import com.semihbkgr.ecommerce.modelcommon.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApi {

    private static final int DEFAULT_USER_PAGE_SIZE = 10;

    private final UserService userService;

    @GetMapping("/get/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/search/{username}")
    public List<UserInfo> searchUserByUsername(@PathVariable("username") String username,
                                               @RequestParam(value = "pageCount", required = false, defaultValue = "0") int pageCount) {
        return userService.searchByUsername(username, Page.of(pageCount, DEFAULT_USER_PAGE_SIZE));
    }

}
