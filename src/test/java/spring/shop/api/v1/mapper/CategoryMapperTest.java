package spring.shop.api.v1.mapper;

import org.junit.jupiter.api.Test;
import spring.shop.api.v1.dto.CategoryDTO;
import spring.shop.model.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
    private static final Long ID = 1L;
    private static final String NAME = "Name";

    @Test
    void categoryToCategoryDTO() {
        Category category = new Category(ID, NAME);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertNotNull(categoryDTO);
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}