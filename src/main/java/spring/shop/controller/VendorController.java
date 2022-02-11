package spring.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.shop.api.v1.dto.VendorDto;
import spring.shop.api.v1.mapper.VendorMapper;
import spring.shop.model.Vendor;
import spring.shop.service.VendorService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("api/v1/vendors")
public class VendorController {

    private final VendorService vendorService;
    private final VendorMapper vendorMapper;

    public VendorController(VendorService vendorService, VendorMapper vendorMapper) {
        this.vendorService = vendorService;
        this.vendorMapper = vendorMapper;
    }

    @GetMapping
    public ResponseEntity<List<VendorDto>> getAllVendors() {
        return new ResponseEntity<>(
                vendorService
                        .getAllVendors()
                        .stream()
                        .map(vendorMapper::vendorToVendorDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDto> getVendorById(@PathVariable Long id) {
        Vendor vendor = vendorService.getVendorById(id);
        VendorDto vendorDto = vendorMapper.vendorToVendorDto(vendor);
        return new ResponseEntity<>(
                vendorDto,
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
        return new ResponseEntity<>(
                vendorService.createVendor(vendor),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable Long id, @RequestBody Vendor vendor) {
        return new ResponseEntity<>(
                vendorService.updateVendor(id, vendor),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteVendor(@PathVariable Long id) {
        return new ResponseEntity<>(vendorService.deleteVendorById(id), HttpStatus.OK);
    }
}
