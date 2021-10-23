package com.semihbkgr.ecommerce.uiserver.controller;

import com.semihbkgr.ecommerce.uiserver.client.UserClient;
import com.semihbkgr.ecommerce.uiserver.util.HeaderUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserClient userClient;

    @GetMapping("/find/{username}")
    public String findUserByUsername(@PathVariable("username") String username, HttpServletRequest request, Model model) {
        var user = userClient.findByUsername(username, HeaderUtils.getAuthenticationHeader(request));
        log.debug("findUserByUsername, username: {}, isUserNonNull: {}", username, user != null);
        model.addAttribute("username",username);
        model.addAttribute("user", user);
        return "user-profile";
    }

    @GetMapping("/search/{username}")
    public String searchUserByUsername(@PathVariable("username") String username, HttpServletRequest request, Model model) {
        var userInfoList = userClient.searchByUsername(username, HeaderUtils.getAuthenticationHeader(request));
        log.debug("searchUserByUsername, username: {}, userInfoListSize: {}", username, userInfoList.size());
        model.addAttribute("username",username);
        model.addAttribute("userInfoList", userInfoList);
        return "user-search";
    }

}
