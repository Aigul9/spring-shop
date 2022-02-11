package spring.shop.api.v1.mapper;

import org.mapstruct.Mapper;
import spring.shop.api.v1.dto.CustomerDTO;
import spring.shop.model.Customer;

@Mapper(componentModel = "spring")
public class CustomerMapper {

    public CustomerDTO customerToCustomerDTO(Customer customer) {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setCustomerUrl("api/v1/customers/" + customer.getId());
        return customerDTO;
    }
}
