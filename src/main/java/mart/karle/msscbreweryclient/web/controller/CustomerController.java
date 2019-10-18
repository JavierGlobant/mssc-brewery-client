package mart.karle.msscbreweryclient.web.controller;

import lombok.RequiredArgsConstructor;
import mart.karle.msscbreweryclient.web.command.CustomerDto;
import mart.karle.msscbreweryclient.web.repository.CustomerRepository;
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
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

  static final String BASE_URL = "/api/v1/customers";

  private final CustomerRepository customerRepository;

  @GetMapping("/{customerId}")
  public CustomerDto getCustomer(@PathVariable final UUID customerId) {
    return customerRepository.getCustomerById(customerId);
  }

  @PostMapping
  public URI createNewCustomer(@RequestBody final CustomerDto customerDto) {
    return customerRepository.createCustomer(customerDto);
  }

  @PutMapping("/{customerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateCustomer(
      @PathVariable final UUID customerId, @RequestBody final CustomerDto customerDto) {
    customerRepository.updateCustomer(customerId, customerDto);
  }

  @DeleteMapping("/{customerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCustomer(@PathVariable final UUID customerId) {
    customerRepository.deleteCustomer(customerId);
  }
}
