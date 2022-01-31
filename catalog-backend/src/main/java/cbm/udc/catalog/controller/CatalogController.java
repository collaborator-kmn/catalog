package cbm.udc.catalog.controller;

import cbm.udc.catalog.dto.CatalogDto;
import cbm.udc.catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Nickolay Burdiladze
 */
@RestController
@RequestMapping("/catalog")
@Slf4j
@RequiredArgsConstructor
public class CatalogController {
    @Autowired
    private CatalogService<CatalogDto> remoteCatalogService;

    @Autowired
    private CatalogService<CatalogDto> defaultCatalogService;

    @GetMapping("/get")
    public ResponseEntity<List<CatalogDto>> catalog() {
        return ResponseEntity.ok(defaultCatalogService.getCatalog());
    }

    @PostMapping("/save")
    public ResponseEntity<List<CatalogDto>> update(@RequestBody List<CatalogDto> value) {
        log.info("Update catalog: {}", value);

        return ResponseEntity.ok().body(defaultCatalogService.saveCatalog(value));
    }
}