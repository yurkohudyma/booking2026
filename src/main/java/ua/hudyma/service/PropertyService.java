package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ua.hudyma.domain.Property;
import ua.hudyma.dto.PropertyReqDto;
import ua.hudyma.dto.PropertyRespDto;
import ua.hudyma.mapper.PropertyMapper;
import ua.hudyma.repository.PropertyRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class PropertyService {
    private final PropertyMapper mapper;
    private final PropertyRepository propertyRepository;

    public PropertyRespDto addProperty (PropertyReqDto dto){
        var property = mapper.toEntity(dto);
        propertyRepository.save(property);
        return mapper.toDto(property);
    }

}
