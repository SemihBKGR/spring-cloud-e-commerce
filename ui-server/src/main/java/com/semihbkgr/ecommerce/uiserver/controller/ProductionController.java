package com.semihbkgr.ecommerce.uiserver.controller;

import com.semihbkgr.ecommerce.uiserver.client.ProductionClient;
import com.semihbkgr.ecommerce.uiserver.util.HeaderUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.HeaderUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/production")
@RequiredArgsConstructor
public class ProductionController {

    private final ProductionClient productionClient;

    @GetMapping("/{production-id}")
    public String findProductionById(@PathVariable("production-id")String productionId, HttpServletRequest request, Model model){
        var production=productionClient.findById(productionId, HeaderUtils.getAuthenticationHeader(request));
        log.debug("findProductionById, production-id: {}, isProductionNonNull: {}", productionId,production!=null);
        model.addAttribute("production",production);
        return "production";
    }

}
