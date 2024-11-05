package me.amiralles.category.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import me.amiralles.category.interceptor.GrpcClientResponseInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public ManagedChannel managedChannel() {
    return ManagedChannelBuilder
            .forAddress(applicationProperties().getHost(),
                        applicationProperties().getPort())
            .intercept(new GrpcClientResponseInterceptor())
            .usePlaintext()
            .build();
  }

  @Bean
  public ApplicationProperties applicationProperties() {
    return new ApplicationProperties();
  }
}
