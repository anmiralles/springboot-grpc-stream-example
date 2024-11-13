package me.amiralles.category.app.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Category {

    @Id
    @Getter
    private Long id;

    @NotNull
    private Integer category;

    @NotNull
    @Size(max = 55, message = "The property 'name' must be less than or equal to 55 characters.")
    private String name;

    @Size(max = 255, message = "The property 'name' must be less than or equal to 255 characters.")
    private String description;
}
