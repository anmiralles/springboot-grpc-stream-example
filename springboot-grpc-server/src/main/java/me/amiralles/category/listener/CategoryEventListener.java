package me.amiralles.category.listener;

import lombok.SneakyThrows;
import me.amiralles.category.event.CategoryEvent;
import me.amiralles.category.service.CategoryService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CategoryEventListener implements ApplicationListener<CategoryEvent> {

    final CategoryService categoryService;

    public CategoryEventListener(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(CategoryEvent event) {
        categoryService.sendCategory(event.getId());
    }
}
