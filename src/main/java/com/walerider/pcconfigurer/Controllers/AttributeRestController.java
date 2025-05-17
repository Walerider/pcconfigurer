package com.walerider.pcconfigurer.Controllers;

import com.walerider.pcconfigurer.DTO.attribute.AttributeRequest;
import com.walerider.pcconfigurer.entities.Attribute;
import com.walerider.pcconfigurer.entities.AttributeValue;
import com.walerider.pcconfigurer.services.AttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attribute")
public class AttributeRestController {
    private final AttributeService attributeService;

    @PostMapping("/create")
    public String create(@RequestBody AttributeRequest request) {
        attributeService.create(request);
        return "attribute created";
    }

    @PostMapping("/create_value")
    public String createAttributeValue(@RequestBody AttributeRequest request) {
        attributeService.createAttributeValue(request);
        return "attribute value created";
    }

    @GetMapping("/get_all")
    public List<Attribute> getAllAttributes() {
        return attributeService.getAllAttributes();
    }

    @GetMapping("/get_all_values")
    public List<AttributeValue> getAllAttributeValues() {
        return attributeService.getAllAttributeValues();
    }
}
