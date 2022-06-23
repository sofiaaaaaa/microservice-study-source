package com.example.catalogservice.controller;

import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.service.CatalogService;
import com.example.catalogservice.vo.ResponseCatalog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @Autowired
    private Environment env;
    @GetMapping("/health_check")
    public String status(){

        return String.format("It's working on port %s", env.getProperty("local.server.port"));
    }
    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getUsers(){
        Iterable<CatalogEntity> catalogs = catalogService.getAllCatalogs();
        List<ResponseCatalog> result = new ArrayList<>();
        catalogs.forEach( v -> {
            result.add(new ModelMapper().map(v, ResponseCatalog.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
