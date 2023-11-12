package com.shop.linepig.domain.menu.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuUpdateRequest {
    private String path;
    private String name;
    private String importPath;
    private String component;
    private Integer sequence;
    private String description;
}
