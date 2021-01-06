package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void getAllCustomer() {
        //given
        Customer customer1 = new Customer(1L,"Bipin","Patel","/api/customer/Bipin");
        Customer customer2 = new Customer(2L,"Adam","Swindler","/api/customer/Adam");
        Customer customer3 = new Customer(3L,"Stewart","Angle","/api/customer/Stewart");

        List<Customer> customers = new ArrayList<Customer>();
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);

        //when
        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.getAllCustomer();

        //then
        assertEquals(customerDTOS.size(),3);
    }

    @Test
    public void getCustomerByName() {
        //given
        Customer customer1 = new Customer(1L,"Bipin","Patel","/api/customer/Bipin");

        //when
        when(customerRepository.findByFirstName(anyString())).thenReturn(customer1);
        CustomerDTO customerDTO = customerService.getCustomerByName("Bipin");
        //then
        assertEquals(customerDTO.getFirstName(),"Bipin");
    }
}