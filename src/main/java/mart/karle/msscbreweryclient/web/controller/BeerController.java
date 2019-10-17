package mart.karle.msscbreweryclient.web.controller;

import lombok.RequiredArgsConstructor;
import mart.karle.msscbreweryclient.web.command.BeerDto;
import mart.karle.msscbreweryclient.web.repository.BeerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
