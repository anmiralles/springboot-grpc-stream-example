package me.amiralles.category.service;

import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import me.amiralles.category.model.Category;
import me.amiralles.category.stubs.CategoryServiceGrpc;
import me.amiralles.category.stubs.GetCategoryRequest;
import me.amiralles.category.stubs.GetCategoryResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryService {

    private final ManagedChannel managedChannel;
    
    public CategoryService(ManagedChannel managedChannel) {
        this.managedChannel = managedChannel;
    }
    
    public void getCategoryAsync(String categoryId) throws InterruptedException {
        log.info("Calling Server..");
        try {
            // Create a new async stub
            CategoryServiceGrpc.CategoryServiceStub categoryServiceAsyncStub = CategoryServiceGrpc
                    .newStub(managedChannel);

            // Define request param
            GetCategoryRequest getCategoryRequest = GetCategoryRequest
                    .newBuilder().setId(categoryId).build();

            // Create async stub
            categoryServiceAsyncStub.getCategory(getCategoryRequest,
                    new CategoryCallback());

            Thread.sleep(3000);
            log.info("Finished call");
        } catch (StatusRuntimeException error) {
            log.error("Error while calling category service, reason {} ",
                    error.getMessage());
        }
    }

    private static class CategoryCallback implements
            StreamObserver<GetCategoryResponse> {

        @Override
        public void onNext(GetCategoryResponse value) {
            Category category = Category.builder()
                    .id(value.getCategory().getId())
                    .category(value.getCategory().getCategory())
                    .name(value.getCategory().getName())
                    .description(value.getCategory().getDescription())
                    .build();
            log.info("Received category, {}", category);
        }

        @Override
        public void onError(Throwable cause) {
            log.error("Error occurred, cause {}", cause.getMessage());
        }

        @Override
        public void onCompleted() {
            log.info("Stream completed");
        }
    }
}
