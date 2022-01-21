package cbm.udc.catalog.service;

import cbm.udc.catalog.dto.CatalogDto;
import org.springframework.stereotype.Service;

/**
 * Nickolay Burdiladze
 */
@Service
public class RemoteCatalogService implements  CatalogService<CatalogDto> {
    @Override
    public CatalogDto catalog() {
        return null;
    }
}