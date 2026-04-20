package ua.hudyma.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hudyma.domain.Property;
import ua.hudyma.dto.PropertyReqDto;
import ua.hudyma.dto.PropertyRespDto;
import ua.hudyma.service.UserService;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class PropertyMapper extends BaseMapper<PropertyRespDto, Property, PropertyReqDto>{

    private final UserService userService;

    @Override
    public PropertyRespDto toDto(Property property) {
        return new PropertyRespDto(
                property.getId(),
                property.getName(),
                property.getPropertyCode(),
                property.getDescription(),
                property.getPropertyType(),
                property.getAddress(),
                property.getGeolocation(),
                property.getDistanceFromCenter(),
                property.getRating(),
                property.getRegisteredOn(),
                property.getRenewedOn(),
                property.getUser().getName()
        );
    }

    @Override
    public Property toEntity(PropertyReqDto dto) {
        var user = userService.getUser(dto.ownerId());
        var property = new Property();
        property.setPropertyType(dto.propertyType());
        property.setDescription(dto.description());
        property.setName(dto.name());
        property.setAddress(dto.address());
        property.setUser(user);
        property.setGeolocation(dto.geolocation());
        property.setRating(BigDecimal.ZERO);
        property.setDistanceFromCenter(dto.distanceFromCenter());
        return property;
    }
}
