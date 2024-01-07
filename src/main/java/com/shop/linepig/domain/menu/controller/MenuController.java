package com.shop.linepig.domain.menu.controller;

import com.shop.linepig.domain.menu.dto.request.MenuCreateRequest;
import com.shop.linepig.domain.menu.dto.request.MenuUpdateRequest;
import com.shop.linepig.domain.menu.dto.response.MenuResponse;
import com.shop.linepig.domain.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/api/menus/{id}")
    public MenuResponse findById(@PathVariable Long id) {
        return menuService.findById(id);
    }

    @GetMapping("/api/menus")
    public List<MenuResponse> findAll() {
        return menuService.findAll();
    }

    @PostMapping("/api/menus")
    public void create(@Validated @RequestBody MenuCreateRequest request) {
        menuService.create(request);
    }

    @PatchMapping("/api/menus/{id}")
    public void update(@PathVariable Long id, @Validated @RequestBody MenuUpdateRequest request) {
        menuService.update(id, request);
    }

    @DeleteMapping("/api/menus/{id}")
    public void delete(@PathVariable Long id) {
        menuService.delete(id);
    }
}
