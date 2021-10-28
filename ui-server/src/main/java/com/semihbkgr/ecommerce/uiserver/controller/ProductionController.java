package com.semihbkgr.ecommerce.uiserver.controller;

import com.semihbkgr.ecommerce.uiserver.client.ImageClient;
import com.semihbkgr.ecommerce.uiserver.client.ProductionClient;
import com.semihbkgr.ecommerce.uiserver.util.HeaderUtils;
import com.semihbkgr.ecommerce.uiserver.util.PrincipalUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.HeaderUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/production")
@RequiredArgsConstructor
public class ProductionController {

    private final ProductionClient productionClient;
    private final ImageClient imageClient;

    @GetMapping("/{production-id}")
    public String findProductionById(@PathVariable("production-id")String productionId, HttpServletRequest request, Model model){
        var production=productionClient.findById(productionId, HeaderUtils.getAuthenticationHeader(request));
        var imageList=imageClient.getAllProductions(productionId);
        var authUsername=PrincipalUtils.getUsername(request);
        log.debug("findProductionById, production-id: {}, isProductionNonNull: {}", productionId,production!=null);
        model.addAttribute("production",production);
        model.addAttribute("images",imageList);
        model.addAttribute("authUsername",authUsername);
        return "production";
    }

    @GetMapping("/search")
    public String searchProduction(@RequestParam("s") String search,
                                   @RequestParam(value = "p",required = false,defaultValue = "0") int page,
                                   HttpServletRequest request,Model model){
        var productionInfoList=productionClient.findAllInfos(search,page);
        var authUsername= PrincipalUtils.getUsername(request);
        model.addAttribute("productionInfos",productionInfoList);
        model.addAttribute("authUsername",authUsername);
        model.addAttribute("search",search);
        return "production-search";
    }

}
