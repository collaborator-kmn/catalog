package cbm.udc.catalog.configuration;

import cbm.udc.catalog.properties.CatalogProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Nickolay Burdiladze
 */
@Configuration
@EnableConfigurationProperties({CatalogProperties.class})
public class CatalogConfiguration {

}