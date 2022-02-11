package spring.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.shop.api.v1.dto.CustomerDto;
import spring.shop.api.v1.mapper.CustomerMapper;
import spring.shop.model.Customer;
import spring.shop.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return new ResponseEntity<>(
                customerService
                        .getAllCustomers()
                        .stream()
                        .map(customerMapper::customerToCustomerDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);
        return new ResponseEntity<>(
                customerDto,
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(
                customerService.createCustomer(customer),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return new ResponseEntity<>(
                customerService.updateCustomer(id, customer),
                HttpStatus.OK);
    }
}
