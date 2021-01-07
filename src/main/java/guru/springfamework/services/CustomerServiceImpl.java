package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.controllers.v1.CustomerController;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return customerRepository.findAll()
               .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getCustomerUrl(customerDTO.getFirstName()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerByName(String name) {
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customerRepository.findByFirstName(name));
        customerDTO.setCustomerUrl(getCustomerUrl(customerDTO.getFirstName()));
        return customerDTO;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        CustomerDTO customerDTO1 = customerMapper.customerToCustomerDTO(customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO)));
        customerDTO1.setCustomerUrl(getCustomerUrl(customerDTO.getFirstName()));
        return  customerDTO1;
    }

    @Override
    public CustomerDTO updateCustomer(String name, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findByFirstName(name);
        customerDTO.setId(customer.getId());
        CustomerDTO customerDTO1 = customerMapper.customerToCustomerDTO(customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO)));
        customerDTO1.setCustomerUrl(getCustomerUrl(customerDTO.getFirstName()));
        return  customerDTO1;

    }

    @Override
    public CustomerDTO patchCustomer(String name, CustomerDTO customerDTO) {
        Customer customer =  customerRepository.findByFirstName(name);

        if (customerDTO.getFirstName() == null) {
            customerDTO.setFirstName(customer.getFirstName());
        }

        if (customerDTO.getLastName() == null) {
            customerDTO.setLastName(customer.getLastName());
        }

        customerDTO.setId(customer.getId());
        CustomerDTO customerDTO1 = customerMapper.customerToCustomerDTO(customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO)));
        customerDTO1.setCustomerUrl(getCustomerUrl(customerDTO.getFirstName()));
        return  customerDTO1;

    }

    @Override
    public void deleteCustomer(String name) {
        Customer customer =  customerRepository.findByFirstName(name);
        customerRepository.delete(customer);
    }

    private String getCustomerUrl(String name) {
        return CustomerController.BASE_URL + "/" + name;
    }
}
