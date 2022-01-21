package cbm.udc.catalog.controller;

import cbm.udc.catalog.dto.CatalogDto;
import cbm.udc.catalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Nickolay Burdiladze
 */
@RestController
public class CatalogController {

    @Autowired
    private CatalogService<List<CatalogDto>> defaultCatalogService;

    @GetMapping("/catalog")
    public ResponseEntity<List<CatalogDto>> catalog() {
        return ResponseEntity.ok(defaultCatalogService.catalog());
    }

    @PostMapping("/update-catalog")
    public ResponseEntity<Void> update() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh-consumers")
    public ResponseEntity<Void> refresh() {
        return ResponseEntity.ok().build();
    }
}