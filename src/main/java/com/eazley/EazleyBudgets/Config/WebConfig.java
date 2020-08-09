package com.eazley.EazleyBudgets.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${client.url}")
    private String clientUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        CorsRegistration corsRegistration = registry.addMapping("/api/**");
        corsRegistration.allowedMethods("DELETE","POST","GET");
        corsRegistration.allowCredentials(true);
        corsRegistration.allowedOrigins(clientUrl);
    }
}
