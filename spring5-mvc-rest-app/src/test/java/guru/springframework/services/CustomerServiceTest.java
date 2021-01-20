package guru.springframework.services;

import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.rest.model.CustomerDTO;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
        Customer customer1 = new Customer(1L,"Bipin","Patel");
        Customer customer2 = new Customer(2L,"Adam","Swindler");
        Customer customer3 = new Customer(3L,"Stewart","Angle");

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
        Customer customer1 = new Customer(1L,"Bipin","Patel");

        //when
        when(customerRepository.findByFirstname(anyString())).thenReturn(customer1);
        CustomerDTO customerDTO = customerService.getCustomerByName("Bipin");
        //then
        assertEquals(customerDTO.getFirstname(),"Bipin");
    }

    @Test
    public void createNewCustomer() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jim");
        customerDTO.setCustomerUrl("/api/v1/customers/Jim");


        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getFirstname());
        savedCustomer.setId(1l);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.saveCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals("/api/v1/customers/Jim", savedDto.getCustomerUrl());
    }

    @Test
    public void updateCustomerByName() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Bipin");
        customerDTO.setCustomerUrl("/api/v1/customers/Bipin");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(1l);

        when(customerRepository.findByFirstname(anyString())).thenReturn(savedCustomer);
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.updateCustomer("Bipin", customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals("/api/v1/customers/Bipin", savedDto.getCustomerUrl());
    }

    @Test
    public void deleteCustomerByname() throws Exception {
        //given
        Customer customer = new Customer();
        customer.setFirstname("test");
        customerRepository.save(customer);
        when(customerRepository.findByFirstname(anyString())).thenReturn(any());
        //when
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("test");
        customerService.deleteCustomer(customerDTO.getFirstname());
        //then
        assertEquals(null, customerRepository.findByFirstname("test"));
    }
}