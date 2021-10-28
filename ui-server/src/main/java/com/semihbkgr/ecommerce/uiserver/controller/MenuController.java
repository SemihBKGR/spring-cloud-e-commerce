package com.semihbkgr.ecommerce.uiserver.controller;

import com.semihbkgr.ecommerce.uiserver.util.PrincipalUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MenuController {

    @GetMapping("/menu")
    public String menu(HttpServletRequest request, Model model){
        var authUsername= PrincipalUtils.getUsername(request);
        model.addAttribute("authUsername",authUsername);
        return "menu";
    }

}
