package spring.shop.service;

import org.springframework.stereotype.Service;
import spring.shop.model.Customer;

import java.util.List;

@Service
public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    Customer createCustomer(Customer customer);
}
