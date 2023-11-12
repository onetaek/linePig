package com.shop.linepig.domain.menu.service;

import com.shop.linepig.domain.menu.dto.request.MenuCreateRequest;
import com.shop.linepig.domain.menu.dto.request.MenuUpdateRequest;
import com.shop.linepig.domain.menu.dto.response.MenuResponse;
import com.shop.linepig.domain.menu.entity.Menu;
import com.shop.linepig.domain.menu.exception.MenuNotFoundException;
import com.shop.linepig.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuResponse findById(Long id) {
        return MenuResponse.fromEntity(menuRepository.findById(id)
                .orElseThrow(MenuNotFoundException::new));
    }

    public List<MenuResponse> findAll() {
        return menuRepository.findAllWitchOrderBy()
                .stream()
                .map(MenuResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public void create(MenuCreateRequest request) {
        Menu menu = request.getMenuId() == null ? null :
                menuRepository.findById(request.getMenuId()).orElseThrow(MenuNotFoundException::new);
        Menu entity = MenuCreateRequest.toEntity(request, menu);
        menuRepository.save(entity);
    }

    public void update(Long id, MenuUpdateRequest request) {
        Menu findMenu = menuRepository.findById(id).orElseThrow(MenuNotFoundException::new);
        findMenu.update(request.getPath(),request.getImportPath(), request.getName(), request.getComponent(),
                request.getDescription(), request.getSequence());
    }

    public void delete(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(MenuNotFoundException::new);
        menuRepository.delete(menu);
    }
}
