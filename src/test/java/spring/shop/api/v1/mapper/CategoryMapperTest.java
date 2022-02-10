package spring.shop.api.v1.mapper;

import org.junit.jupiter.api.Test;
import spring.shop.api.v1.dto.CategoryDTO;
import spring.shop.model.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
    private static final Long id = 1L;
    private static final String name = "Name";

    @Test
    void categoryToCategoryDTO() {
        //given
        Category category = new Category(id, name);
        
        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertNotNull(categoryDTO);
        assertEquals(id, categoryDTO.getId());
        assertEquals(name, categoryDTO.getName());
    }
}