package com.semihbkgr.ecommerce.uiserver.controller;

import com.semihbkgr.ecommerce.uiserver.client.ProductionClient;
import com.semihbkgr.ecommerce.uiserver.client.UserClient;
import com.semihbkgr.ecommerce.uiserver.util.PrincipalUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserClient userClient;
    private final ProductionClient productionClient;

    @GetMapping("/{username}")
    public String findUserByUsername(@PathVariable("username") String username, HttpServletRequest request, Model model) {
        var user = userClient.findByUsername(username);
        var productionInfList = productionClient.findAllInfosByOwner(username, 0);
        var authUsername = PrincipalUtils.getUsername(request);
        log.debug("findUserByUsername, username: {}, isUserNonNull: {}", username, user != null);
        model.addAttribute("username", username);
        model.addAttribute("authUsername", authUsername);
        model.addAttribute("user", user);
        model.addAttribute("productionInfos", productionInfList);
        model.addAttribute("owned", username.equals(authUsername));
        return "profile";
    }

    @GetMapping("/search/{username}")
    public String searchUserByUsername(@PathVariable("username") String username, Model model) {
        var userInfoList = userClient.searchByUsername(username);
        log.debug("searchUserByUsername, username: {}, userInfoListSize: {}", username, userInfoList.size());
        model.addAttribute("username", username);
        model.addAttribute("userInfoList", userInfoList);
        return "user-search";
    }

}
