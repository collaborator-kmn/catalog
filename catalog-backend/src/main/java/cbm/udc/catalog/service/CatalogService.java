package cbm.udc.catalog.service;

import java.util.List;

/**
 * Nickolay Burdiladze
 */
public interface CatalogService<T> {
    List<T> getCatalog();

    List<T> saveCatalog(List<T> catalog);
}