package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getAllCustomers() throws Exception {
        //given
        CustomerDTO customer1 = new CustomerDTO(1L,"Bipin","Patel","/api/customer/Bipin");
        CustomerDTO customer2 = new CustomerDTO(2L,"Adam","Swindler","/api/customer/Adam");
        CustomerDTO customer3 = new CustomerDTO(3L,"Stewart","Angle","/api/customer/Stewart");
        List<CustomerDTO> customers = new ArrayList<CustomerDTO>();
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);

        //when
        when(customerService.getAllCustomer()).thenReturn(customers);
        //then
        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(3)));

    }

    @Test
    public void getCustomerByName() throws Exception {
        //given
        CustomerDTO customer1 = new CustomerDTO(1L,"Bipin","Patel","/api/customer/Bipin");
        //when
        when(customerService.getCustomerByName(anyString())).thenReturn(customer1);
        //then
        //note the BIPIN in url
        mockMvc.perform(get("/api/v1/customers/Bipin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Bipin")));
    }
}