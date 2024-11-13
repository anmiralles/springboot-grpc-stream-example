package me.amiralles.category.app.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CategoryEvent extends ApplicationEvent {

    private final String name;
    private final String id;

    public CategoryEvent(Object source, String name, String id) {
        super(source);
        this.name = name;
        this.id = id;
    }

}
