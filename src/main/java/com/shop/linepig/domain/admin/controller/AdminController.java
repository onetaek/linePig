package com.shop.linepig.domain.admin.controller;

import com.shop.linepig.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admins/login")
    public String loginPage() {
        return "/admins/login";
    }

    @GetMapping(value ={"/admins/welcome","/admins"})
    public String welcomePage() {
        return "/admins/welcome";
    }

    @GetMapping("/admins/products/new")
    public String productNewPage() {
        return "/admins/products/new";
    }




}
