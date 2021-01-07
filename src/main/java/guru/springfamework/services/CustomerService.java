package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomer();

    CustomerDTO getCustomerByName(String name);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(String name, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(String name, CustomerDTO customerDTO);

    CustomerDTO deleteCustomer(String name);
}
