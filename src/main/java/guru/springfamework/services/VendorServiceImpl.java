package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getAllVendor() {
        return vendorRepository.findAll()
                .stream()
                .map(vendorMapper::vendorToVendorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorByName(String name) {
        return vendorMapper.vendorToVendorDTO(vendorRepository.findByName(name));
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        try {
            return vendorMapper.vendorToVendorDTO(vendorRepository.findById(id).get());
        } catch (RuntimeException rex) {throw
                new ResourceNotFoundException();
        }
    }

    @Override
    public VendorDTO saveVendor(VendorDTO vendorDTO) {

        Vendor vendorToSave = vendorMapper.vendorDTOToVendor(vendorDTO);

        return saveAndReturnDTO(vendorToSave);
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        return vendorMapper.vendorToVendorDTO(vendorRepository.save(vendorMapper.vendorDTOToVendor(vendorDTO)));
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        try {
            Vendor vendor = vendorRepository.findById(id).get();
            if (vendorDTO.getName() != null) {
                vendor.setName(vendorDTO.getName());
            }

            return saveAndReturnDTO(vendor);
        } catch (RuntimeException rex) {
            throw new ResourceNotFoundException();
        }
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor){
            Vendor savedVendor = vendorRepository.save(vendor);

            VendorDTO returnDto = vendorMapper.vendorToVendorDTO(savedVendor);

            returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));

            return returnDto;
        }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }


    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }
}
