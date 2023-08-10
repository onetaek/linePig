package com.shop.linepig.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.member.entity.enumeration.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
public class GenderResponse {

    private final String code;
    private final String display;
    private final Integer sequence;
    private String description;

    public static GenderResponse fromEnum(Gender gender) {
        if (gender == null)
            return null;
        return new GenderResponse(
                gender.getCode(),
                gender.getDescription(),
                gender.getSequence(),
                gender.getDescription());
    }
}
