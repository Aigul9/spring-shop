package spring.shop.service;

import org.springframework.stereotype.Service;
import spring.shop.api.v1.dto.CustomerDTO;

import java.util.List;

@Service
public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);
}
