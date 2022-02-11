package spring.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.shop.api.v1.dto.CustomerDto;
import spring.shop.api.v1.mapper.CustomerMapper;
import spring.shop.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/v1/customers/")
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

    @GetMapping("{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(
                customerMapper.customerToCustomerDto(customerService.getCustomerById(id)),
                HttpStatus.OK);
    }
}
