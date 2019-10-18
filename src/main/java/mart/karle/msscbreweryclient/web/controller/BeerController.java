package mart.karle.msscbreweryclient.web.controller;

import lombok.RequiredArgsConstructor;
import mart.karle.msscbreweryclient.web.command.BeerDto;
import mart.karle.msscbreweryclient.web.repository.BeerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(BeerController.BASE_URL)
public class BeerController {

  static final String BASE_URL = "/api/v1/beers";

  private final BeerRepository beerRepository;

  @GetMapping("/{beerId}")
  public BeerDto getBeer(@PathVariable final UUID beerId) {
    return beerRepository.getBeerById(beerId);
  }

  @PostMapping
  public URI createNewBeer(@RequestBody final BeerDto beerDto) {
    return beerRepository.createBeer(beerDto);
  }

  @PutMapping("/{beerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateBeer(@PathVariable final UUID beerId, @RequestBody final BeerDto beerDto) {
    beerRepository.updateBeer(beerId, beerDto);
  }

  @DeleteMapping("/{beerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBeer(@PathVariable final UUID beerId) {
    beerRepository.deleteBeer(beerId);
  }
}
