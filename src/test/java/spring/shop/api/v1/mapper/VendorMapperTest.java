package spring.shop.api.v1.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.shop.api.v1.dto.VendorDto;
import spring.shop.model.Vendor;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = VendorMapper.class)
class VendorMapperTest {

    @Autowired
    VendorMapper vendorMapper;

    private static final String NAME = "vendorName";

    @Test
    void VendorToVendorDTO() {
        Vendor vendor = new Vendor(NAME);

        VendorDto vendorDTO = vendorMapper.vendorToVendorDto(vendor);

        assertNotNull(vendorDTO);
        assertEquals(NAME, vendorDTO.getName());
    }
}