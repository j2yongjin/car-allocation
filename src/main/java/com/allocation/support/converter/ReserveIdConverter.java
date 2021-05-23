package com.allocation.support.converter;

import com.allocation.reservation.domain.ReserveId;
import com.allocation.reservation.domain.ReserveType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ReserveIdConverter implements AttributeConverter<ReserveId,String> {
    @Override
    public String convertToDatabaseColumn(ReserveId attribute) {
        return attribute.getReserveId();
    }

    @Override
    public ReserveId convertToEntityAttribute(String dbData) {
        return ReserveId.of(ReserveType.ofReserveType(dbData.substring(0,4))
                ,dbData.substring(4,16),dbData.substring(16,28));
    }
}
