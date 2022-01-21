package cbm.udc.catalog.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Nickolay Burdiladze
 */
@Data
@ConfigurationProperties("catalog")
public class CatalogProperties {

    @NestedConfigurationProperty
    private CamundaUdcProperties camundaUdcProperties;

    @Data
    static class CamundaUdcProperties {
        private String url;
        private String user;
        private String password;
    }
}