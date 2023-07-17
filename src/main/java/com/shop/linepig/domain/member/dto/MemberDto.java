package com.shop.linepig.domain.member.dto;


import com.shop.linepig.domain.member.entity.Member;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Getter
public class MemberDto {

    private Long id;
    private String name;

    public static MemberDto fromEntity(Member member) {
        if (member == null)
            return null;
        return MemberDto.of(
                member.getId(),
                member.getName());
    }
}
