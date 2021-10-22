package com.semihbkgr.ecommerce.uiserver.controller;

import com.semihbkgr.ecommerce.uiserver.client.UserClient;
import com.semihbkgr.ecommerce.uiserver.util.HeaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserClient userClient;

    @GetMapping("/get/{username}")
    public String getUserByUsername(@PathVariable("username") String username, HttpServletRequest request, Model model) {
        model.addAttribute("user", userClient.getByUsername(username, HeaderUtils.getAuthenticationHeader(request)));
        return "user-profile";
    }

    @GetMapping("/search/{username}")
    public String searchUserByUsername(@PathVariable("username") String username, HttpServletRequest request, Model model) {
        model.addAttribute("userInfoList", userClient.searchByUsername(username, HeaderUtils.getAuthenticationHeader(request)));
        return "user-search";
    }

}
