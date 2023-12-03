package nl.novi.backendtechiteasy.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import nl.novi.backendtechiteasy.enums.AvailableSizes;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class AvailableSizeConverter implements AttributeConverter<AvailableSizes, Long> {
    @Override
    public Long convertToDatabaseColumn(AvailableSizes availableSizes) {
        if (availableSizes == null) {
            return null;
        }

        return availableSizes.getScreenSize();
    }

    @Override
    public AvailableSizes convertToEntityAttribute(Long screenSize) {
        if (screenSize == null) {
            return null;
        }

        return Stream.of(AvailableSizes.values())
                .filter(s -> s.getScreenSize().equals(screenSize))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
