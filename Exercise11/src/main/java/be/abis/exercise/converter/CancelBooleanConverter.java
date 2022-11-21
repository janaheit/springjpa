package be.abis.exercise.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CancelBooleanConverter implements AttributeConverter<Boolean, String> {


    @Override
    public String convertToDatabaseColumn(Boolean b) {
        if (b) return "C";
        else return null;
    }

    @Override
    public Boolean convertToEntityAttribute(String s) {
        if (s==null) return false;
        else if (s.equals("C") || s.equals("c"))  return true;

        return false;
    }
}
