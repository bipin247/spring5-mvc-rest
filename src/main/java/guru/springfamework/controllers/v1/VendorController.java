package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags={"This is my Vendor Controller"})
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value="This returns list of vendors",response=Iterable.class)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getListOfVendors(){
        return vendorService.getAllVendor();
    }

    @ApiOperation(value="This returns a vendor for a given id",response=Vendor.class)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendor(@PathVariable Long id){
        return vendorService.getVendorById(id);
    }

    @ApiOperation(value="This saves a vendor for vendor data",response=VendorDTO.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createVendor(@RequestBody VendorDTO vendorDTO){
        return vendorService.saveVendor(vendorDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.updateVendor(id, vendorDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO){
        return vendorService.patchVendor(id, vendorDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id){
        vendorService.deleteVendor(id);
    }

}
