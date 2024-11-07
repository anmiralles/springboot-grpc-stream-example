package me.amiralles.category.service;

import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import me.amiralles.category.stubs.EntityRequest;
import me.amiralles.category.stubs.EntityResponse;
import me.amiralles.category.stubs.EntityServiceGrpc;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EntityService {

    private final ManagedChannel managedChannel;

    public EntityService(ManagedChannel managedChannel) {
        this.managedChannel = managedChannel;
    }

    public void sendEntityRequest(String entityName, String entityId) throws InterruptedException {
        try {
            log.info("Sending request for entity {}:{}", entityName, entityId);

            // Create a new sync stub
            EntityServiceGrpc.EntityServiceBlockingStub entityServiceBlockingStub =
                    EntityServiceGrpc.newBlockingStub(managedChannel);

            // Building request
            EntityRequest entityRequest = EntityRequest.newBuilder().setName(entityName).setId(entityId).build();

            // Building response
            EntityResponse entityResponse = entityServiceBlockingStub.getEntityById(entityRequest);

            log.info("Receiving response for entity {}", entityResponse);
        } catch (StatusRuntimeException error) {
            log.error("Error while calling entity service, reason {} ",
                    error.getMessage());
        }
    }

}
