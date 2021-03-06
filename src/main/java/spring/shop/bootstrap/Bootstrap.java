package spring.shop.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
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
        log.debug("===Data Loaded===");
        log.info(String.valueOf(categoryRepository.count()));
    }
}
