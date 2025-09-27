package com.email.writer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailGeneratorService {
    @Value("${gemini.api.url}")
    private String geminiApiUrl;
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public String generateEmailReply(EmailRequest emailRequest){
        String prompt=new buildPrompt(emailRequest);
        Map<String,Object> requestBody=Map.of("content",new Object[]{
            Map.of("parts",new Object[]{
                Map.of("text",prompt)
            })
        }
        );



    }

    private String buildPrompt(EmailRequest emailRequest){
        StringBuilder prompt= new StringBuilder();
        prompt.append("Generate a professional");
        if (emailRequest.getTone()!=null&& !emailRequest.getTone().isEmpty()){
            prompt.append(" use a").append(emailRequest.getTone()).append("tone");
        }
        prompt.append("\nOriginal email: \n").append(emailRequest.getEmailContent());
        return prompt.toString();
    }
}
