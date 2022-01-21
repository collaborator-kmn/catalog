package cbm.udc.catalog.service;

import cbm.udc.catalog.dto.CatalogDto;
import cbm.udc.catalog.dto.CuratorDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Nickolay Burdiladze
 */
@Service
public class DefaultCatalogService implements CatalogService<List<CatalogDto>> {

    @Override
    public List<CatalogDto> catalog() {
        return List.of(
                CatalogDto.builder()
                        .arm("arm_1")
                        .boss("boss_1")
                        .curators(List.of(
                                CuratorDto.builder().name("1").build(),
                                CuratorDto.builder().name("2").build(),
                                CuratorDto.builder().name("3 ").build()
                        ))
                        .build()
        );
    }
}