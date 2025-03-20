package com.email.email_writer_sb.app.config;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @GetMapping
    public Map<String, String> getConfig() {
        String backendUrl = System.getenv("BACKEND_URL");
        String frontendUrl = System.getenv("FRONTEND_URL");

        return Map.of(
                "backendUrl", backendUrl != null ? backendUrl : "http://localhost:8080",
                "frontendUrl", frontendUrl != null ? frontendUrl : "http://localhost:3000"
        );
    }
}
