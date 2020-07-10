package com.thecodinginterface.todobackend.models;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TodoStatusConverter implements AttributeConverter<TodoStatus, String> {

    @Override
    public String convertToDatabaseColumn(TodoStatus todoStatus) {
        if (todoStatus == null) {
            return null;
        }
        return todoStatus.getCode();
    }

    @Override
    public TodoStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return Stream.of(TodoStatus.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
