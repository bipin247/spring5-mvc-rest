package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getListofCustomers(){

        return new ResponseEntity<CustomerListDTO>
                (new CustomerListDTO(customerService.getAllCustomer()), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String name){
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.saveCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{name}")
    public ResponseEntity<CustomerDTO> saveCustomer(@PathVariable String name ,@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.updateCustomer(name,customerDTO), HttpStatus.OK);
    }

    @PatchMapping("/{name}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable String name ,@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.patchCustomer(name,customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> patchCustomer(@PathVariable String name ){
        customerService.deleteCustomer(name);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
