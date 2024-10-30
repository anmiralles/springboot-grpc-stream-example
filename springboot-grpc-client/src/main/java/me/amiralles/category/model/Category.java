package me.amiralles.category.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class Category {
    Long id;
    Integer category;
    String name;
    String description;
}
