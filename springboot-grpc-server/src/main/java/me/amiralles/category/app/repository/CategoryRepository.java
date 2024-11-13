package me.amiralles.category.app.repository;

import lombok.val;
import me.amiralles.category.app.model.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CategoryRepository extends ReactiveSortingRepository<Category, UUID> {

    Flux<Category> findAllBy(Pageable pageable);

    Mono<Category> findByCategory(Integer category);

    default Mono<Category> updateCategory(Category category) {
        val existingCategory = findByCategory(category.getCategory()).block();

        if (existingCategory != null) {
            existingCategory.setCategory(category.getCategory());
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
        }
        return Mono.just(existingCategory);
    }

    Mono<Long> count();
}
