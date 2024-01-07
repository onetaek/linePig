package com.shop.linepig.domain.menu.entity;

import com.shop.linepig.domain.common.mappedsuperclass.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
@SQLDelete(sql = "UPDATE MENU SET deleted = 1, deleted_on = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted = false")
public class Menu extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;//경로

    private String importPath;//import 경로

    private String name;//이름

    private String component;//컴포넌트 경로

    private String description;//비고

    private Integer sequence;//순서

    private Boolean isActive;//사용여부

    @ManyToOne
    private Menu parentMenu;

    @Builder.Default
    @OneToMany(mappedBy = "parentMenu", cascade = CascadeType.ALL)
    private List<Menu> childMenus = new ArrayList<>();

    public void update(String path, String importPath, String name, String component, String description, Integer sequence) {
        if(path != null) this.path = path;
        if(importPath != null) this.importPath = importPath;
        if(name != null) this.name = name;
        if(component != null) this.component = component;
        if(description != null) this.description = description;
        if(sequence != null) this.sequence = sequence;
    }

    public Boolean hasChild() {
        return this.childMenus != null && this.childMenus.size() != 0;
    }
}
