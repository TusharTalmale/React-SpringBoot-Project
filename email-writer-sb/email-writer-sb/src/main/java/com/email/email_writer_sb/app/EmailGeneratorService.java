package com.email.email_writer_sb.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class EmailGeneratorService {
    private final WebClient webClient;
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public EmailGeneratorService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.build();
    }


    public String generateEmailReply(EmailRequest emailRequest){
        //Build A prompt
        String prompt = buildPrompt(emailRequest);

        //Craft a Request
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {  //building object array directly here
                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        //Do request and get Response
        String response = webClient.post()
                .uri(geminiApiUrl+ geminiApiKey)
                .header("Content-Type","application/json").bodyValue(requestBody)
                .retrieve().bodyToMono(String.class).block();



        //Extract response return response
        return extractResponseContent(response);




    }

    private String extractResponseContent(String response) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            return rootNode.path("candidates")
                            .path(0)
                            .path("content")
                            .path("parts")
                            .path(0)
                            .path("text")
                            .asText();
        } catch (Exception e) {
            return "Error processing Request : "+ e.getMessage();
        }
    }


    //building of actual prompt Function
    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append(" Generate a professional email reply for the following email content. Please don't generate a subject line for Professional.");
        if (emailRequest.getTone() !=null && !emailRequest.getTone().isEmpty()){
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone . ");
        }
        prompt.append("\nOriginal Email : \n").append(emailRequest.getEmailContent());
        return prompt.toString();
    }
}
