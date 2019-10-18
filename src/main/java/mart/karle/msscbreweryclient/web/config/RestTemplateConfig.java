package mart.karle.msscbreweryclient.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(value = "rest-template", ignoreUnknownFields = false)
public class RestTemplateConfig {
  private Integer maxTotalConnections;
  private Integer defaultMaxTotalConnections;
  private Integer connectionRequestTimeout;
  private Integer socketTimeout;
}
