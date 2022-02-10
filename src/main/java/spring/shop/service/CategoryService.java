package spring.shop.service;


import org.springframework.stereotype.Service;
import spring.shop.api.v1.dto.CategoryDTO;

import java.util.List;

@Service
public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
