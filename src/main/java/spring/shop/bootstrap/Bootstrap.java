package spring.shop.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.shop.model.Category;
import spring.shop.repository.CategoryRepository;

@Component
@Slf4j
public class Bootstrap implements CommandLineRunner { // CLR - spring boot specific class, runs on startup

    private final CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {

        Category fruits = new Category("Fruits"),
                dried = new Category("Dried"),
                fresh = new Category("Fresh"),
                exotic = new Category("Exotic"),
                nuts = new Category("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        log.debug("===Data Loaded===");
        log.debug(String.valueOf(categoryRepository.count()));
    }
}
