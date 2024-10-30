package me.amiralles.category.controller;

import lombok.RequiredArgsConstructor;
import me.amiralles.category.model.Category;
import me.amiralles.category.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping
    public Mono<Page<Category>> findAllCategories(Pageable pageable) {
        return this.categoryRepository.findAllBy(pageable)
                .collectList()
                .zipWith(this.categoryRepository.count())
                .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));
    }

    @GetMapping("/{category}")
    public Mono<Category> getCategory(@PathVariable(name = "category") String category) {
        return categoryRepository.findByCategory(Integer.parseInt(category));
    }

}
