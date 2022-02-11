package spring.shop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.shop.api.v1.mapper.CustomerMapper;
import spring.shop.model.Customer;
import spring.shop.repository.CustomerRepository;
import spring.shop.service.impl.CustomerServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = CustomerMapper.class)
class CustomerServiceTest {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> res = customerService.getAllCustomers();

        assertEquals(3, res.size());
    }

    @Test
    void testGetCustomerById() {
        Customer customer = new Customer(FIRST_NAME, LAST_NAME);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        Customer res = customerService.getCustomerById(1L);

        assertEquals(FIRST_NAME, res.getFirstName());
        assertEquals(LAST_NAME, res.getLastName());
    }

    @Test
    void testCreateCustomer() {

        Customer customer = new Customer(FIRST_NAME, LAST_NAME);
        customer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer res = customerService.createCustomer(customer);
        
        assertEquals(customer.getFirstName(), res.getFirstName());
        assertEquals(customer.getLastName(), res.getLastName());
    }

    @Test
    void testUpdateCustomer() {
        Customer customer = new Customer(FIRST_NAME, LAST_NAME);
        customer.setId(1L);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer res = customerService.updateCustomer(1L, customer);

        assertEquals(customer.getFirstName(), res.getFirstName());
        assertEquals(customer.getLastName(), res.getLastName());
    }
}