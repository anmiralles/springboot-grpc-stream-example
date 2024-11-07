package me.amiralles.category.service;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import me.amiralles.category.stubs.Category;
import me.amiralles.category.stubs.CategoryServiceGrpc;
import me.amiralles.category.stubs.GetCategoryRequest;
import me.amiralles.category.stubs.GetCategoryResponse;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class CategoryGrpcService extends CategoryServiceGrpc.CategoryServiceImplBase {

    @Override
    public void getCategory(GetCategoryRequest request,
                            StreamObserver<GetCategoryResponse> responseObserver) {
        log.info("gRPC Category server called");

        Category requestCategory = request.getCategory();

        log.info("gRPC Category received: {}", requestCategory);

        var getCategoryResponse = GetCategoryResponse
                    .newBuilder().setAck("category received").build();

        responseObserver.onNext(getCategoryResponse);
        responseObserver.onCompleted();

        log.info("Finished calling Category gRPC service");
    }
}
