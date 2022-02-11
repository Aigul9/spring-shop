package spring.shop.service;


import org.springframework.stereotype.Service;
import spring.shop.model.Category;

import java.util.List;

@Service
public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryByName(String name);
}
