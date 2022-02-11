package spring.shop.service.impl;

import org.springframework.stereotype.Service;
import spring.shop.model.Vendor;
import spring.shop.repository.VendorRepository;
import spring.shop.service.VendorService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getVendorById(Long id) {

        Optional<Vendor> vendorOptional = vendorRepository.findById(id);
        if (vendorOptional.isPresent()) {
            return vendorOptional.get();
        } else {
            throw new NoSuchElementException("Vendor not found, id: " + id);
        }
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor updateVendor(Long id, Vendor vendor) {

        Optional<Vendor> vendorById = vendorRepository.findById(id);

        if (vendorById.isPresent()) {
            Vendor oldVendor = vendorById.get();
            oldVendor.setName(vendor.getName());

            return vendorRepository.save(oldVendor);
        } else {
            throw new NoSuchElementException("Vendor not found, id: " + id);
        }
    }

    @Override
    public Long deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
        return id;
    }
}
