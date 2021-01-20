package guru.springframework.services;

import guru.rest.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomer();

    CustomerDTO getCustomerByName(String name);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(String name, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(String name, CustomerDTO customerDTO);

    void deleteCustomer(String name);

    CustomerDTO getCustomerById(long l);

    CustomerDTO saveCustomerByDTO(long l, CustomerDTO customerDTO);
}
