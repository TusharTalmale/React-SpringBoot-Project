package com.email.email_writer_sb.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailGeneratorController {
    private final EmailGeneratorService emailGeneratorService;
    public EmailGeneratorController(EmailGeneratorService emailGeneratorService) {
        this.emailGeneratorService = emailGeneratorService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@RequestBody EmailRequest emailRequest) {
        if (emailRequest == null) {
            return ResponseEntity.badRequest().body("EmailRequest cannot be null");
        }

        try {
            String response = emailGeneratorService.generateEmailReply(emailRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error generating email: " + e.getMessage());
        }
    }

}
