package mart.karle.msscbreweryclient.web.config;

import lombok.RequiredArgsConstructor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

  private final RestTemplateConfig restTemplateConfig;

  private ClientHttpRequestFactory clientHttpRequestFactory() {
    final PoolingHttpClientConnectionManager connectionManager =
        new PoolingHttpClientConnectionManager();
    connectionManager.setMaxTotal(restTemplateConfig.getMaxTotalConnections());
    connectionManager.setDefaultMaxPerRoute(restTemplateConfig.getDefaultMaxTotalConnections());

    final RequestConfig requestConfig =
        RequestConfig.custom()
            .setConnectionRequestTimeout(restTemplateConfig.getConnectionRequestTimeout())
            .setSocketTimeout(restTemplateConfig.getSocketTimeout())
            .build();

    final CloseableHttpClient httpClient =
        HttpClients.custom()
            .setConnectionManager(connectionManager)
            .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
            .setDefaultRequestConfig(requestConfig)
            .build();

    return new HttpComponentsClientHttpRequestFactory(httpClient);
  }

  @Override
  public void customize(final RestTemplate restTemplate) {
    restTemplate.setRequestFactory(clientHttpRequestFactory());
  }
}
