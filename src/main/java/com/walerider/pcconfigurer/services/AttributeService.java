package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.DTO.attribute.AttributeRequest;
import com.walerider.pcconfigurer.entities.Attribute;
import com.walerider.pcconfigurer.entities.AttributeValue;
import com.walerider.pcconfigurer.repositories.AttributeRepository;
import com.walerider.pcconfigurer.repositories.AttributeValuesRepository;
import com.walerider.pcconfigurer.validation.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeService {
    private final AttributeRepository attributeRepository;
    private final AttributeValuesRepository attributeValuesRepository;

    public void create(AttributeRequest request) {
        if (attributeRepository.existsByName(request.getField())) {
            throw new BadRequestException("the attribute name must by unique");
        }
        attributeRepository.save(new Attribute(request.getField()));
    }

    public void createAttributeValue(AttributeRequest request) {
        if (attributeValuesRepository.existsByValue(request.getField())) {
            throw new BadRequestException("the attribute value must by unique");
        }
        attributeValuesRepository.save(new AttributeValue(request.getField()));
    }

    public List<Attribute> getAllAttributes() {
        return attributeRepository.findAll();
    }

    public List<AttributeValue> getAllAttributeValues() {
        return attributeValuesRepository.findAll();
    }
}
