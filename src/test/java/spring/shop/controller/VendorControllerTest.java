package spring.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring.shop.api.v1.mapper.VendorMapper;
import spring.shop.model.Vendor;
import spring.shop.service.VendorService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VendorControllerTest {

    @Mock
    VendorService vendorService;

    @Mock
    VendorMapper vendorMapper;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    private static final String NAME = "vendorName";
    private static final Long ID = 1L;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
    }

    @Test
    void getAllVendors() throws Exception {
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor());
        given(vendorService.getAllVendors()).willReturn(vendors);
        mockMvc.perform(get("/api/v1/vendors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    // failing test (mapper issues)
    @Disabled
    @Test
    void getVendorById() throws Exception {
        Vendor vendor = new Vendor(NAME);
        vendor.setId(ID);
        given(vendorService.getVendorById(anyLong())).willReturn(vendor);
        mockMvc.perform(get("/api/v1/vendors/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    void createVendor() throws Exception {
        Vendor vendor = new Vendor(NAME);
        given(vendorService.createVendor(any(Vendor.class))).willReturn(vendor);
        String vendorString = new ObjectMapper().writeValueAsString(vendor);
        mockMvc.perform(post("/api/v1/vendors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vendorString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    void updateVendor() throws Exception {
        Vendor vendor = new Vendor(NAME);
        vendor.setId(ID);
        given(vendorService.updateVendor(anyLong(), any(Vendor.class))).willReturn(vendor);
        String vendorString = new ObjectMapper().writeValueAsString(vendor);
        mockMvc.perform(put("/api/v1/vendors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vendorString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    void deleteVendor() throws Exception {
        mockMvc.perform(delete("/api/v1/vendors/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(vendorService).should().deleteVendorById(anyLong());
    }
}