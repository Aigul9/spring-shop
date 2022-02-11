package spring.shop.api.v1.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.shop.api.v1.dto.CustomerDTO;
import spring.shop.model.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = CustomerMapper.class)
public class CustomerMapperTest {

    @Autowired
    CustomerMapper customerMapper;

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";

    @Test
    void customerToCustomerDTO() {
        Customer customer = new Customer(FIRST_NAME, LAST_NAME);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertNotNull(customerDTO);
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }
}
