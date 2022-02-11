package spring.shop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.shop.api.v1.mapper.VendorMapper;
import spring.shop.model.Vendor;
import spring.shop.repository.VendorRepository;
import spring.shop.service.impl.VendorServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = VendorMapper.class)
class VendorServiceTest {

    private static final String NAME = "vendorName";

    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @Autowired
    VendorMapper vendorMapper;

    @BeforeEach
    void setUp() {
        vendorService = new VendorServiceImpl(vendorRepository);
    }

    @Test
    void getAllVendors() {
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor());
        given(vendorRepository.findAll()).willReturn(vendors);
        List<Vendor> res = vendorService.getAllVendors();
        then(vendorRepository).should(times(1)).findAll();
        assertThat(res.size(), is(equalTo(vendors.size())));
    }

    @Test
    void getVendorById() {
        Vendor vendor = new Vendor(NAME);

        //BDD
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        Vendor res = vendorService.getVendorById(1L);
        then(vendorRepository).should(times(1)).findById(anyLong());

        assertThat(res.getName(), is(equalTo(NAME)));
    }

    @Test
    void createVendor() {
        Vendor vendor = new Vendor(NAME);
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);
        Vendor res = vendorService.createVendor(vendor);
        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(res.getName(), is(equalTo(vendor.getName())));
    }

    @Test
    void updateVendor() {
        Vendor vendor = new Vendor(NAME);
        vendor.setId(1L);

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        Vendor res = vendorService.updateVendor(1L, vendor);

        assertEquals(vendor.getName(), res.getName());
    }

    @Test
    void deleteVendorById() {
        Long id = 1L;
        vendorService.deleteVendorById(id);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}