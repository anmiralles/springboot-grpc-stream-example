package me.amiralles.category.service;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import me.amiralles.category.model.Category;
import me.amiralles.category.repository.CategoryRepository;
import me.amiralles.category.stubs.CategoryServiceGrpc;
import me.amiralles.category.stubs.GetCategoryRequest;
import me.amiralles.category.stubs.GetCategoryResponse;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@GrpcService
public class CategoryGrpcService extends CategoryServiceGrpc.CategoryServiceImplBase {

    private final CategoryRepository categoryRepository;

    public CategoryGrpcService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void getCategory(GetCategoryRequest request,
                            StreamObserver<GetCategoryResponse> responseObserver) {
        log.info("Calling Category gRPC service");

        String productId = request.getId();

        Mono<Category> categoryMono = categoryRepository
                .findByCategory(Integer.valueOf(productId));

        if(categoryMono != null &&
                Objects.requireNonNull(categoryMono.block()).getId() > 0) {
            var categoryResponse = me.amiralles.category.stubs.Category.newBuilder()
                    .setId(categoryMono.block().getId())
                    .setCategory(categoryMono.block().getCategory())
                    .setName(categoryMono.block().getName())
                    .setDescription(categoryMono.block().getDescription())
                    .build();

            var getCategoryResponse = GetCategoryResponse
                    .newBuilder().setCategory(categoryResponse).build();

            responseObserver.onNext(getCategoryResponse);
            responseObserver.onCompleted();

            log.info("Finished calling Category gRPC service");
        }
    }
}
