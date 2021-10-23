package com.semihbkgr.ecommerce.ecommerce.userserver.api;

import com.semihbkgr.ecommerce.ecommerce.userserver.service.UserService;
import com.semihbkgr.ecommerce.ecommerce.userserver.util.Page;
import com.semihbkgr.ecommerce.modelcommon.user.User;
import com.semihbkgr.ecommerce.modelcommon.user.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApi {

    private static final int DEFAULT_USER_PAGE_SIZE = 10;

    private final UserService userService;

    @GetMapping("/find/{username}")
    public User findUserByUsername(@PathVariable("username") String username,
                                   HttpServletResponse response) {
        var user = userService.findByUsername(username);
        log.debug("findUserByUsername, username: {}, isUserNonNull: {}", username, user != null);
        if (user == null)
            response.setStatus(HttpStatus.NOT_FOUND.value());
        else
            response.setStatus(HttpStatus.OK.value());
        return user;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search/{username}")
    public List<UserInfo> searchUserByUsername(@PathVariable("username") String username,
                                               @RequestParam(value = "pageCount", required = false, defaultValue = "0") int pageCount) {
        var userInfoList = userService.searchByUsername(username, Page.of(pageCount, DEFAULT_USER_PAGE_SIZE));
        log.debug("searchUserByUsername, username: {}, pageCount: {}, userInfoListSize: {}", username, pageCount, userInfoList.size());
        return userInfoList;
    }

}
