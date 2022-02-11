package spring.shop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.shop.api.v1.dto.CategoryDTO;
import spring.shop.api.v1.mapper.CategoryMapper;
import spring.shop.model.Category;
import spring.shop.repository.CategoryRepository;
import spring.shop.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {

        Category category = categoryRepository.findByNameIgnoreCase(name);
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
        return categoryDTO;
    }
}
