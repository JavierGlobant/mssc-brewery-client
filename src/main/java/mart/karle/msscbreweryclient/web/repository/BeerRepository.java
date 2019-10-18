package mart.karle.msscbreweryclient.web.repository;

import lombok.RequiredArgsConstructor;
import mart.karle.msscbreweryclient.web.command.BeerDto;
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
public class BeerRepository {
  private final RestTemplate restTemplate;
  private final BreweryServiceConfig breweryServiceConfig;

  public BeerDto getBeerById(final UUID id) {
    final Map<String, Object> uriVars = Collections.singletonMap(Constants.BEER_ID, id.toString());
    final URI uri = breweryServiceConfig.getUri(Constants.GET_BEER_BY_ID, uriVars);
    return restTemplate.getForObject(uri, BeerDto.class);
  }

  public URI createBeer(final BeerDto beerDto) {
    final URI uri = breweryServiceConfig.getUri(Constants.CREATE_BEER);
    return restTemplate.postForLocation(uri, beerDto);
  }

  public void updateBeer(final UUID beerId, final BeerDto beerDto) {
    final URI uri =
        breweryServiceConfig.getUri(
            Constants.UPDATE_BEER_BY_ID, Collections.singletonMap(Constants.BEER_ID, beerId));
    restTemplate.put(uri, beerDto);
  }

  public void deleteBeer(final UUID beerId) {
    final URI uri =
        breweryServiceConfig.getUri(
            Constants.DELETE_BEER_BY_ID, Collections.singletonMap(Constants.BEER_ID, beerId));
    restTemplate.delete(uri);
  }
}
