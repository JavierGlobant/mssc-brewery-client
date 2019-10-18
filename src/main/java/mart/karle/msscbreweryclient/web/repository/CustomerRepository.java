package mart.karle.msscbreweryclient.web.repository;

import lombok.RequiredArgsConstructor;
import mart.karle.msscbreweryclient.web.command.CustomerDto;
import mart.karle.msscbreweryclient.web.config.BreweryServiceConfig;
import mart.karle.msscbreweryclient.web.utils.Constants;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {
  private final RestTemplate restTemplate;
  private final BreweryServiceConfig breweryServiceConfig;

  public CustomerDto getCustomerById(final UUID id) {
    final Map<String, Object> uriVars =
        Collections.singletonMap(Constants.CUSTOMER_ID, id.toString());
    final URI uri = breweryServiceConfig.getUri(Constants.GET_CUSTOMER_BY_ID, uriVars);
    return restTemplate.getForObject(uri, CustomerDto.class);
  }

  public URI createCustomer(final CustomerDto customerDto) {
    final URI uri = breweryServiceConfig.getUri(Constants.CREATE_CUSTOMER);
    return restTemplate.postForLocation(uri, customerDto);
  }

  public void updateCustomer(final UUID customerId, final CustomerDto customerDto) {
    final URI uri =
        breweryServiceConfig.getUri(
            Constants.UPDATE_CUSTOMER_BY_ID,
            Collections.singletonMap(Constants.CUSTOMER_ID, customerId));
    restTemplate.put(uri, customerDto);
  }

  public void deleteCustomer(final UUID customerId) {
    final URI uri =
        breweryServiceConfig.getUri(
            Constants.DELETE_CUSTOMER_BY_ID,
            Collections.singletonMap(Constants.CUSTOMER_ID, customerId));
    restTemplate.delete(uri);
  }
}
