package com.example.catalogservice.service;

import com.example.catalogservice.CatalogRepository;
import com.example.catalogservice.jpa.CatalogEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    CatalogRepository catalogRepository;

    @Override
    public Iterable<CatalogEntity> getAllCatalogs() {
        return catalogRepository.findAll();
    }

}
