package com.allocation.support.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDateTime attribute) {
        if(attribute == null) return null;
        return Date.from(attribute.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date dbData) {
        if(dbData == null) return null;
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(dbData.getTime()),ZoneId.systemDefault());
    }
}
