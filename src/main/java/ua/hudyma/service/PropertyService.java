package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.Property;
import ua.hudyma.dto.*;
import ua.hudyma.enums.CityCenters;
import ua.hudyma.mapper.PropertyMapper;
import ua.hudyma.repository.PropertyRepository;
import ua.hudyma.util.DistanceCalculator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class PropertyService {

    private final PropertyMapper mapper;

    private final PropertyRepository propertyRepository;

    private final UserService userService;

    @Transactional
    public PropertyRespDto addProperty(PropertyReqDto dto) {
        var property = mapper.toEntity(dto);
        var owner = userService.getUser(dto.ownerId());
        propertyRepository.save(property);
        owner.getPropertyList().add(property);
        return mapper.toDto(property);
    }

    @Transactional(readOnly = true)
    public List<PropertyRespDto> getPropertyList(String ownerId) {
        var owner = userService.getUser(ownerId);
        return owner.getPropertyList()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public Property getProperty(String propertyId) {
        return propertyRepository.findByPropertyCode(propertyId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Property" + propertyId + " NOT found"));
    }

    public List<String> getPropertiesElement(String query) {
        return propertyRepository
                .findAll()
                .stream()
                .map(Property::getAddress)
                .map(extractAddressElement(getIndex(query)))
                .toList();
    }

    private static int getIndex(String query) {
        return switch (query) {
            case "cities" -> 1;
            case "countries" -> 2;
            default -> throw new IllegalArgumentException
                    ("query not recognised");
        };
    }

    public Function<String, String> extractAddressElement(Integer index) {
        return address -> address.split(",")[index].strip();
    }

    public List<PropertyRespDto> getAllBy(String query, String object) {
        var index = switch (object) {
            case "city" -> 1;
            case "country" -> 2;
            default -> throw new IllegalArgumentException("address field type is not recognised");
        };
        return propertyRepository
                .findAll()
                .stream()
                .filter(property -> extractAddressElement(index)
                        .apply(property.getAddress())
                        .equals(query))
                .map(mapper::toDto)
                .toList();
    }
    public List<PropertyRespDto> getAllByRating(BigDecimal rating) {
        return propertyRepository
                .findAllByRatingGreaterThanEqual(rating)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
    public List<PropertyRespDto> getAllByDistanceFromCityCenter(String city, Double distance) {
        if (!isRegistered(city)) throw new IllegalArgumentException(city + " not REGISTERED");
        return propertyRepository
                .findAllByDistanceFromCenterLessThanEqual(distance)
                .stream()
                .filter(prop ->
                        extractAddressElement(1).apply(prop.getAddress()).equals(city))
                .map(mapper::toDto)
                .toList();
    }
    private boolean isRegistered(String city) {
        return Arrays
                .stream(CityCenters.values())
                .anyMatch(constant -> constant.name().equalsIgnoreCase(city));
    }

    @Transactional
    public Double getDistanceFromCenter(String propertyCode) {
        var property = getProperty(propertyCode);
        var propertyLocation = convertToDoubleGeoLocation(property.getGeolocation());
        var cityCenterLocation = getCityCenterLocation(property.getAddress());
        var distance = DistanceCalculator.haversine(
                new DistanceDto(
                        propertyLocation.latitude(),
                        propertyLocation.longitude(),
                        cityCenterLocation.latitude(),
                        cityCenterLocation.longitude()));
        property.setDistanceFromCenter(distance);
        return distance;
    }

    private DoubleGeolocation getCityCenterLocation(String address) {
        var city = extractAddressElement(1).apply(address).toUpperCase();
        var lat = CityCenters.valueOf(city).getLatitude();
        var lon = CityCenters.valueOf(city).getLongitude();
        return new DoubleGeolocation(lat, lon);
    }

    private DoubleGeolocation convertToDoubleGeoLocation(Geolocation geolocation) {
        if (geolocation == null)
            throw new IllegalArgumentException("Cannot calculate, geolocation is NULL");
        return new DoubleGeolocation(
                geolocation.latitude().doubleValue(),
                geolocation.longitude().doubleValue());
    }
}