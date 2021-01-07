package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getListofCustomers(){
        return new CustomerListDTO(customerService.getAllCustomer());
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable String name){
        return customerService.getCustomerByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.saveCustomer(customerDTO);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO saveCustomer(@PathVariable String name ,@RequestBody CustomerDTO customerDTO){
        return customerService.updateCustomer(name,customerDTO);
    }

    @PatchMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable String name ,@RequestBody CustomerDTO customerDTO){
        return customerService.patchCustomer(name,customerDTO);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void patchCustomer(@PathVariable String name ){
        customerService.deleteCustomer(name);
    }

}
