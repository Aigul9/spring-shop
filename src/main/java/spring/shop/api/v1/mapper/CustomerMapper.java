package spring.shop.api.v1.mapper;

import org.mapstruct.Mapper;
import spring.shop.api.v1.dto.CustomerDto;
import spring.shop.model.Customer;

@Mapper(componentModel = "spring")
public class CustomerMapper {

    public CustomerDto customerToCustomerDto(Customer customer) {

        CustomerDto customerDTO = new CustomerDto();
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setCustomerUrl("api/v1/customers/" + customer.getId());
        return customerDTO;
    }

    public Customer customerDtoToCustomer(CustomerDto customerDto) {
        return new Customer(customerDto.getFirstName(), customerDto.getLastName());
    }
}
