package me.amiralles.category.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import me.amiralles.category.interceptor.GrpcClientRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public ManagedChannel managedChannel() {
    return ManagedChannelBuilder
            .forAddress(applicationProperties().getHost(),
                        applicationProperties().getPort())
            .intercept(new GrpcClientRequestInterceptor())
            .usePlaintext()
            .build();
  }

  @Bean
  public ApplicationProperties applicationProperties() {
    return new ApplicationProperties();
  }
}
