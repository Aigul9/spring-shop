package spring.shop.service;

import org.springframework.stereotype.Service;
import spring.shop.model.Vendor;

import java.util.List;

@Service
public interface VendorService {

    List<Vendor> getAllVendors();

    Vendor getVendorById(Long id);

    Vendor createVendor(Vendor Vendor);

    Vendor updateVendor(Long id, Vendor Vendor);

    Long deleteVendorById(Long id);
}
