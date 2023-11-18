package com.shop.linepig.domain.menu.dto.response;

import com.shop.linepig.domain.menu.entity.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class MenuResponse {

    private Long id;
    private String path;//경로
    private String importPath;//import 경로
    private String name;//이름
    private String component;//컴포넌트 경로
    private Integer sequence;//순서
    private String description;//비고
    private Boolean isActive;//사용여부
    private Boolean hasChild;//자식이 유무
    private List<MenuResponse> childMenus;

    public static MenuResponse fromEntity(Menu entity) {
        if (entity == null) return null;
        return MenuResponse.builder()
                .id(entity.getId())
                .path(entity.getPath())
                .importPath(entity.getImportPath())
                .name(entity.getName())
                .component(entity.getComponent())
                .sequence(entity.getSequence())
                .description(entity.getDescription())
                .isActive(entity.getIsActive())
                .hasChild(entity.hasChild())
                .childMenus(entity.getChildMenus()
                        .stream()
                        .map(MenuResponse::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }

}
