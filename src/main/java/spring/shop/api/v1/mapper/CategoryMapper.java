package spring.shop.api.v1.mapper;

import org.mapstruct.Mapper;
import spring.shop.api.v1.dto.CategoryDto;
import spring.shop.model.Category;

@Mapper(componentModel = "spring")
public class CategoryMapper {

    public CategoryDto categoryToCategoryDto(Category category) {
        return new CategoryDto(category.getName());
    }
}
