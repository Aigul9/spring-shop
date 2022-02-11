package spring.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.shop.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByNameIgnoreCase(String name);
}
