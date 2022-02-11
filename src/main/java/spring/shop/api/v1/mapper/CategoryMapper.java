package spring.shop.api.v1.mapper;

import org.mapstruct.Mapper;
import spring.shop.api.v1.dto.CategoryDTO;
import spring.shop.model.Category;

@Mapper(componentModel = "spring")
public class CategoryMapper {

    public CategoryDTO categoryToCategoryDTO(Category category) {
        return new CategoryDTO(category.getName());
    }
}
