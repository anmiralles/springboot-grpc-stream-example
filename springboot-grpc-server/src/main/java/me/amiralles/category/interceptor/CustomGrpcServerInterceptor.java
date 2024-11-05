package me.amiralles.category.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Optional;

@Slf4j
public class CustomGrpcServerInterceptor implements ServerInterceptor {

    private static final String POD_NAME = "HOSTNAME";
    private static final String HOST_NAME = "localhost";

    @Autowired
    private Environment environment;

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall,
                                                                 Metadata metadata,
                                                                 ServerCallHandler<ReqT, RespT> serverCallHandler) {
        log.info("Received following metadata: {}", metadata);
        metadata.put(Metadata.Key.of("HOSTNAME", Metadata.ASCII_STRING_MARSHALLER),
                     Optional.ofNullable(environment.getProperty(POD_NAME)).orElse(HOST_NAME));
        log.info("Adding metadata: {}", metadata);
        return serverCallHandler.startCall(serverCall, metadata);
    }

}
