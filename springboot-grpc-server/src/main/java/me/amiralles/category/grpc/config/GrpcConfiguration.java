package me.amiralles.category.grpc.config;

import me.amiralles.category.app.config.MetadataInjector;
import me.amiralles.category.grpc.GrpcClientRequestInterceptor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class GrpcConfiguration {

    @GrpcGlobalClientInterceptor
    public GrpcClientRequestInterceptor globalClientRequestInterceptor() {
        return new GrpcClientRequestInterceptor();
    }

    @Bean
    public MetadataInjector metadataInjector() {
        return new MetadataInjector();
    }
}
