package cbm.udc.catalog.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Nickolay Burdiladze
 */
@Data
@Builder
public class CatalogDto {
    private String arm;
    private String boss;
    private List<CuratorDto> curators;
}