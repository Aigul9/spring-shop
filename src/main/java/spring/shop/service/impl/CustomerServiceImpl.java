package spring.shop.service.impl;

import org.springframework.stereotype.Service;
import spring.shop.model.Customer;
import spring.shop.repository.CustomerRepository;
import spring.shop.service.CustomerService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {

        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            throw new NoSuchElementException("Customer not found, id: " + id);
        }
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {

        Optional<Customer> customerById = customerRepository.findById(id);

        if (customerById.isPresent()) {
            Customer oldCustomer = customerById.get();
            oldCustomer.setFirstName(customer.getFirstName());
            oldCustomer.setLastName(customer.getLastName());

            return customerRepository.save(oldCustomer);
        } else {
            throw new NoSuchElementException("Customer not found, id: " + id);
        }
    }

    @Override
    public Long deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
        return id;
    }
}
