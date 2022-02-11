package spring.shop.service.impl;

import org.springframework.stereotype.Service;
import spring.shop.api.v1.dto.CustomerDTO;
import spring.shop.api.v1.mapper.CustomerMapper;
import spring.shop.model.Customer;
import spring.shop.repository.CustomerRepository;
import spring.shop.service.CustomerService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return customerMapper.customerToCustomerDTO(customer);
        } else {
            throw new NoSuchElementException("Customer not found, id: " + id);
        }
    }
}
