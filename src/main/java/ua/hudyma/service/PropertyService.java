package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hudyma.domain.Property;
import ua.hudyma.dto.PropertyReqDto;
import ua.hudyma.dto.PropertyRespDto;
import ua.hudyma.mapper.PropertyMapper;
import ua.hudyma.repository.PropertyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PropertyService {
    private final PropertyMapper mapper;
    private final PropertyRepository propertyRepository;
    private final UserService userService;

    @Transactional
    public PropertyRespDto addProperty (PropertyReqDto dto){
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
        var index = switch (query){
            case "cities" -> 1;
            case "countries" -> 2;
            default -> throw new IllegalArgumentException
                    ("query not recognised");
        };
        return propertyRepository
                .findAll()
                .stream()
                .map(Property::getAddress)
                .map(address -> address.split(","))
                .map(address -> address[index])
                .map(String::strip)
                .toList();
    }
}