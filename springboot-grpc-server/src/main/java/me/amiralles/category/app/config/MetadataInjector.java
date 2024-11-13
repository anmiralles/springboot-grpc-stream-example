package me.amiralles.category.app.config;

import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MetadataInjector {

    private static final String POD_NAME = "HOSTNAME";
    private static final String HOST_NAME = "localhost";

    public String getPodName() {
        Environment environment = new StandardEnvironment();
        return Optional.ofNullable(environment.getProperty(POD_NAME)).orElse(HOST_NAME);
    }
}
