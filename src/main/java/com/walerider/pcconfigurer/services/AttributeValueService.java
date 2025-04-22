package com.walerider.pcconfigurer.services;

import com.walerider.pcconfigurer.entities.AttributeValue;
import com.walerider.pcconfigurer.repositories.AttributeValuesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AttributeValueService {
    private final AttributeValuesRepository attributeValuesRepository;

    @Autowired
    public AttributeValueService(AttributeValuesRepository attributeValuesRepository) {
        this.attributeValuesRepository = attributeValuesRepository;
    }
}
