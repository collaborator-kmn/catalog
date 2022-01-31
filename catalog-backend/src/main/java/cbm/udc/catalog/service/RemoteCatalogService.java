package cbm.udc.catalog.service;

import cbm.udc.catalog.dto.CatalogDto;
import cbm.udc.catalog.properties.CatalogProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Nickolay Burdiladze
 */
@Service
public class RemoteCatalogService implements  CatalogService<CatalogDto> {
    private final RestTemplate restTemplate;
    private final CatalogProperties catalogProperties;

    public RemoteCatalogService(RestTemplateBuilder builder, CatalogProperties catalogProperties) {
        this.catalogProperties = catalogProperties;

        restTemplate = builder.
                basicAuthentication(
                        catalogProperties.getCamundaUdcProperties().getUser(),
                        catalogProperties.getCamundaUdcProperties().getPassword())
                .build();
    }

    @Override
    public List<CatalogDto> getCatalog() {
        return Optional.of(restTemplate.exchange(
                catalogProperties.getCamundaUdcProperties().getUrl(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CatalogDto>>() {
                }
        ))
                .map(HttpEntity::getBody)
                .orElse(Collections.emptyList());
    }

    @Override
    public List<CatalogDto> saveCatalog(List<CatalogDto> catalog) {
        //todo: add logic
        return catalog;
    }
}