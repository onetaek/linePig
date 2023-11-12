package com.shop.linepig.domain.menu.dto.request;

import com.shop.linepig.domain.menu.entity.Menu;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MenuCreateRequest {
    private Long menuId;//부모 메뉴가 있는 경우
    @NotBlank
    private String path;
    @NotBlank
    private String importPath;
    @NotBlank
    private String name;
    @NotBlank
    private String component;
    @NotNull
    private Integer sequence;
    private String description;

    public static Menu toEntity(MenuCreateRequest request, Menu menu) {
        return Menu.builder()
                .path(request.getPath())
                .importPath(request.getImportPath())
                .name(request.getName())
                .component(request.getComponent())
                .description(request.getDescription())
                .sequence(request.getSequence())
                .parentMenu(menu)
                .build();
    }
}
