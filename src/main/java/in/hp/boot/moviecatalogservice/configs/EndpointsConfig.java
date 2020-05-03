package in.hp.boot.moviecatalogservice.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("endpoints")
@Data
public class EndpointsConfig {
    private String userInfoService;
    private String movieInfoService;
    private String ratingDataService;
    private String version;
}
