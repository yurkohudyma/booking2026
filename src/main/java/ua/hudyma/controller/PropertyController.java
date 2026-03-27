package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.PropertyReqDto;
import ua.hudyma.dto.PropertyRespDto;
import ua.hudyma.service.PropertyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/property")
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PropertyRespDto> addProperty (
            @RequestBody PropertyReqDto dto){
        return ResponseEntity.ok(propertyService.addProperty(dto));
    }

    @GetMapping
    public ResponseEntity<List<PropertyRespDto>> getProperty (
            @RequestParam String ownerId){
        return ResponseEntity.ok(propertyService.getPropertyList(ownerId));
    }

    @GetMapping("/get")
    public ResponseEntity<List<String>> retrieveAddressElementFromProperties (
            @RequestParam String query){
        return ResponseEntity.ok(propertyService.getPropertiesElement(query));
    }

    @GetMapping("/getAllByCity")
    public ResponseEntity<List<PropertyRespDto>> getAllByCity (
            @RequestParam String city){
        return ResponseEntity.ok(propertyService.getAllByCity (city));
    }
}
