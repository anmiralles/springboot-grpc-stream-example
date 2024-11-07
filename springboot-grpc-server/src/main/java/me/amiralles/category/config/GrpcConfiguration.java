package me.amiralles.category.config;

import me.amiralles.category.interceptor.GrpcClientRequestInterceptor;
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
