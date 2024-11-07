package me.amiralles.category.controller;

import me.amiralles.category.service.EntityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntityController {

    private final EntityService entityService;

    public EntityController(EntityService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/entity/{entityName}/{entityId}")
    public void getCategory(@PathVariable(name = "entityName") String entityName,
                            @PathVariable(name = "entityId") String entityId)
            throws InterruptedException {
        entityService.sendEntityRequest(entityName, entityId);
    }
}
