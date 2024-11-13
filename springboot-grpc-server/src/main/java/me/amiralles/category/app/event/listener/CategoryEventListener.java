package me.amiralles.category.app.event.listener;

import lombok.SneakyThrows;
import me.amiralles.category.app.event.CategoryEvent;
import me.amiralles.category.app.service.CategoryService;
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
