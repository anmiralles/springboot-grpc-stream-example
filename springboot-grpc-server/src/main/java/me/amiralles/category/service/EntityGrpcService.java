package me.amiralles.category.service;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import me.amiralles.category.event.CategoryEvent;
import me.amiralles.category.stubs.EntityRequest;
import me.amiralles.category.stubs.EntityResponse;
import me.amiralles.category.stubs.EntityServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
@GrpcService
public class EntityGrpcService extends EntityServiceGrpc.EntityServiceImplBase {

    private final ApplicationEventPublisher applicationEventPublisher;

    public EntityGrpcService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void getEntityById(EntityRequest request,
                              StreamObserver<EntityResponse> responseObserver) {
        // Retrieving request params
        String entityName = request.getName();
        String entityId = request.getId();

        log.info("Sending response for entity {}:{}", entityName, entityId);

        // Sent event
        final CategoryEvent categoryEvent = new CategoryEvent(this, entityName, entityId);
        applicationEventPublisher.publishEvent(categoryEvent);

        var entityResponse = EntityResponse
                .newBuilder().setAck("Request for: " + entityName + ":" + entityId).build();

        responseObserver.onNext(entityResponse);
        responseObserver.onCompleted();

        log.info("Finished sending response for entity {}:{}", entityName, entityId);
    }

}
