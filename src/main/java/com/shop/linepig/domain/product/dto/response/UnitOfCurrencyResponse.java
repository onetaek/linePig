package com.shop.linepig.domain.product.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.product.entity.enumeration.UnitOfCurrency;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
public class UnitOfCurrencyResponse {

    private final String code;
    private final String displayValue;
    private final Integer sequence;
    private String description;

    public static UnitOfCurrencyResponse fromEnum(UnitOfCurrency unitOfCurrency) {
        if (unitOfCurrency == null)
            return null;
        return new UnitOfCurrencyResponse(
                unitOfCurrency.getCode(),
                unitOfCurrency.getDisplayValue(),
                unitOfCurrency.getSequence(),
                unitOfCurrency.getDescription());
    }

}
