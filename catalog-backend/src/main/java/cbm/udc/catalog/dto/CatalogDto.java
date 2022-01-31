package cbm.udc.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Nickolay Burdiladze
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogDto {
    private String arm;
    private String boss;
    private List<CuratorDto> curators;
}