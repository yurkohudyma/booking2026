package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hudyma.dto.PropertyReqDto;
import ua.hudyma.dto.PropertyRespDto;
import ua.hudyma.service.PropertyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/property")
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PropertyRespDto> addProperty (@RequestBody PropertyReqDto dto){
        return ResponseEntity.ok(propertyService.addProperty(dto));
    }
}
