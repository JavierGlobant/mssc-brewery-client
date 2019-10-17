package mart.karle.msscbreweryclient.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "services.brewery", ignoreUnknownFields = false)
public class BreweryServiceConfig extends ServiceConfig {}
