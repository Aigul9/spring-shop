package spring.shop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.shop.api.v1.mapper.CategoryMapper;
import spring.shop.model.Category;
import spring.shop.repository.CategoryRepository;
import spring.shop.service.impl.CategoryServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = CategoryMapper.class)
class CategoryServiceTest {

    private static final String NAME = "Name";

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void getAllCategories() {
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> res = categoryService.getAllCategories();

        assertEquals(3, res.size());
    }

    @Test
    void getCategoryByName() {
        Category category = new Category(NAME);
        when(categoryRepository.findByNameIgnoreCase(anyString())).thenReturn(category);

        Category res = categoryService.getCategoryByName(NAME);

        assertEquals(NAME, res.getName());
    }
}