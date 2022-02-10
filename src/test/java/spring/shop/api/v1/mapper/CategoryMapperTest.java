package spring.shop.api.v1.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.shop.api.v1.dto.CategoryDTO;
import spring.shop.model.Category;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CategoryMapper.class)
class CategoryMapperTest {

    @Autowired
    CategoryMapper categoryMapper;

    private static final Long ID = 1L;
    private static final String name = "Name";

    @Test
    void categoryToCategoryDTO() {
        Category category = new Category(ID, name);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertNotNull(categoryDTO);
        assertEquals(ID, categoryDTO.getId());
        assertEquals(name, categoryDTO.getName());
    }
}