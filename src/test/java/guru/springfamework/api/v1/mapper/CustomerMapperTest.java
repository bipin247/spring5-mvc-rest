package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Test;
import org.springframework.web.bind.annotation.InitBinder;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    public static final long ID = 123L;
    public static final String FIRST_NAME = "James";
    public static final String LAST_NAME = "Smith";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws Exception {

        //given
        Customer customer = new Customer(ID, FIRST_NAME, LAST_NAME);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(customerDTO.getFirstName(), FIRST_NAME);

    }

    @Test
    public void customerDTOToCustomer() {

        //given
        CustomerDTO customerDTO = new CustomerDTO(ID, FIRST_NAME, LAST_NAME,"/api/v1/customers/" + FIRST_NAME);

        //when
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        //then
        assertEquals(customer.getFirstName(), FIRST_NAME);

    }
}
