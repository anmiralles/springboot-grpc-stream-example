package me.amiralles.category.controller;

import me.amiralles.category.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category/{categoryId}")
    public void getCategory(@PathVariable(name = "categoryId") String categoryId)
            throws InterruptedException {
        categoryService.getCategoryAsync(categoryId);
    }
}
