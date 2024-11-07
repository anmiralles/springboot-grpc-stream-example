package me.amiralles.category.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("springboot-micro1")
@Setter
@Getter
public class ApplicationProperties {
    private String host;
    private int port;
}
