package me.amiralles.category.config;

import me.amiralles.category.interceptor.CustomGrpcServerInterceptor;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class GrpcConfiguration {

    @GrpcGlobalServerInterceptor
    public CustomGrpcServerInterceptor grpcServerInterceptor() {
        return new CustomGrpcServerInterceptor();
    }
}
