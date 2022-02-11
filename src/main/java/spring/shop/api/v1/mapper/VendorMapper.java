package spring.shop.api.v1.mapper;

import org.mapstruct.Mapper;
import spring.shop.api.v1.dto.VendorDto;
import spring.shop.model.Vendor;

@Mapper(componentModel = "spring")
public class VendorMapper {

    public VendorDto vendorToVendorDto(Vendor vendor) {

        VendorDto vendorDto = new VendorDto();
        vendorDto.setName(vendor.getName());
        vendorDto.setVendorUrl("api/v1/vendors/" + vendor.getId());
        return vendorDto;
    }
}
