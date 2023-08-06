package com.shop.linepig.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.member.entity.enumeration.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
public class MemberStatusResponse {

    private final String code;
    private final String displayValue;
    private final Integer sequence;
    private String description;

    public static MemberStatusResponse fromEnum(MemberStatus memberStatus) {
        return new MemberStatusResponse(
                memberStatus.getCode(),
                memberStatus.getDisplayValue(),
                memberStatus.getSequence(),
                memberStatus.getDescription());
    }
}
