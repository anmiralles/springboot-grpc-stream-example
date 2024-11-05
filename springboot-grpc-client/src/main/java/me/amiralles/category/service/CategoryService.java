package me.amiralles.category.service;

import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
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
            // Create a new sync stub
            CategoryServiceGrpc.CategoryServiceBlockingStub categoryServiceBlockingStub =
                    CategoryServiceGrpc.newBlockingStub(managedChannel);

            // Define request param
            GetCategoryRequest getCategoryRequest = GetCategoryRequest
                    .newBuilder().setId(categoryId).build();

            // Create request and process response
            GetCategoryResponse category = categoryServiceBlockingStub
                    .getCategory(getCategoryRequest);
            //log.info("Received category, {}", category);
        } catch (StatusRuntimeException error) {
            log.error("Error while calling category service, reason {} ",
                    error.getMessage());
        }
        log.info("Finished call");
    }

    /*private static class CategoryCallback implements
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
    }*/
}
