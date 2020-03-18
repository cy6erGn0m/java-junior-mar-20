package ru.levelup.db;

import ru.levelup.model.Color;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ColorConverter implements AttributeConverter<Color, String> {
    @Override
    public String convertToDatabaseColumn(Color attribute) {
        return attribute.getR() + "," + attribute.getG() + "," + attribute.getB();
    }

    @Override
    public Color convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            throw new NullPointerException("Failed to convert null to Color");
        }
        String[] components = dbData.split(",");
        if (components.length != 3) {
            throw new IllegalArgumentException("Failed to convert " + dbData + " to Color");
        }

        return new Color(
                Integer.parseInt(components[0]),
                Integer.parseInt(components[1]),
                Integer.parseInt(components[2])
        );
    }
}
