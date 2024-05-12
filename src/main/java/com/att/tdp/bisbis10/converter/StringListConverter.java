package com.att.tdp.bisbis10.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;

/*
This class used to convert data from database stored as array to list and vice versa.
* */
@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private static final String DELIMITER = ",";

    /*
    This method gets a list of strings and convert it to string representing PostgreSQL array format.
    Used to store list in database as array.
    * */
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }

        String stringData = String.join(DELIMITER, attribute);

        return "{" + String.join(",", stringData) + "}";

    }

    /*
    This method gets a string representing PostgreSQL array format and convert it to list of strings.
    Used to read data from database stored as array.
    * */
    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }

        String newDbData = dbData.replace("{", "");
        newDbData = newDbData.replace("}", "");

        return Arrays.asList(newDbData.split(DELIMITER));
    }

}