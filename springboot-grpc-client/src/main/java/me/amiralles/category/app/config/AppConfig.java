package me.amiralles.category.app.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public ManagedChannel managedChannel() {
    return ManagedChannelBuilder
            .forAddress(applicationProperties().getHost(),
                        applicationProperties().getPort())
            .usePlaintext()
            .build();
  }

  @Bean
  public ApplicationProperties applicationProperties() {
    return new ApplicationProperties();
  }
}
