package me.amiralles.category.service;

import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import me.amiralles.category.model.Category;
import me.amiralles.category.repository.CategoryRepository;
import me.amiralles.category.stubs.CategoryServiceGrpc;
import me.amiralles.category.stubs.GetCategoryRequest;
import me.amiralles.category.stubs.GetCategoryResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Service
public class CategoryService {

    private final ManagedChannel managedChannel;
    private final CategoryRepository categoryRepository;
    
    public CategoryService(ManagedChannel managedChannel, CategoryRepository categoryRepository) {
        this.managedChannel = managedChannel;
        this.categoryRepository = categoryRepository;
    }

    public void sendCategory(String categoryId) throws InterruptedException {
        try {
            log.info("Sending request for categoryId: {}", categoryId);

            // Create a new sync stub
            CategoryServiceGrpc.CategoryServiceBlockingStub categoryServiceBlockingStub =
                    CategoryServiceGrpc.newBlockingStub(managedChannel);

            // Building request
            Mono<Category> categoryMono = categoryRepository.findByCategory(Integer.valueOf(categoryId));

            if(categoryMono != null && Objects.requireNonNull(categoryMono.block()).getId() > 0) {
                me.amiralles.category.stubs.Category stubCategory = me.amiralles.category.stubs.Category
                        .newBuilder()
                        .setId(categoryMono.block().getId())
                        .setCategory(categoryMono.block().getCategory())
                        .setName(categoryMono.block().getName())
                        .setDescription(categoryMono.block().getDescription())
                        .build();

                GetCategoryRequest getCategoryRequest = GetCategoryRequest
                        .newBuilder().setCategory(stubCategory).build();

                // Building response
                GetCategoryResponse categoryResponse = categoryServiceBlockingStub
                        .getCategory(getCategoryRequest);

                log.info("Receiving response for category {}", categoryResponse);
            }
        } catch (StatusRuntimeException error) {
            log.error("Error while calling category service, reason {} ",
                    error.getMessage());
        }
    }    
}
