package com.instamail.backend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class EmailGeneratorService {

    private final WebClient webClient;


    // these values will not be hardcoded it will be injected from application.properties file
    @Value("${gemini.api.url}")
    private  String geminiApiUrl;
    @Value("${gemini.api.key}")
    private  String geminiApiKey;

    public EmailGeneratorService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.build();
    }


    public String generateEmailReply(EmailRequest emailRequest){
        // Build the prompt
        String prompt = buildPrompt(emailRequest);

        // Craft a request as per the format of gemini api sample shown in postman
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {

                        Map.of("parts", new Object[]{
                                Map.of("text", prompt)
                        })
                }
        );

        // Do req and get response
        String response = webClient.post() // post because this is a post request
                .uri(geminiApiUrl +  geminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Extract Response and Return
        return extractResponseContent(response);
    }

    private String extractResponseContent(String response) {
        try{
            ObjectMapper mapper = new ObjectMapper(); // ObjectMapper can convert json data to java obj, java objects to json data and vise versa
            JsonNode rootNode = mapper.readTree(response); // readTree is a method turns the json response into a tree-like structure,
            // this tree like structure is represented by JsonNode, and it represents the entire json tree
            //using the object of JsonNode that is "rootNode" we can navigate through the entire tree that is the benifit of it
            return rootNode.path("candidates") // to get the response in the format same as the response format returned in postman
                    .get(0) // to get first item/element of candidates that is content parts as this is an array
                    .path("content")
                    .path("parts")
                    .get(0) // to get the first item of content -> parts -> that is "text"
                    .path("text")
                    .asText(); // convert the array element into string datatype
        }catch(Exception e) {
            return  "Error processing request: " +e.getMessage();
        }
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder(); // create an empty string builder obj
        prompt.append("Generate a professional email reply for the following email content. Please don't generate a subject line ");
        if(emailRequest.getTone() !=null && !emailRequest.getTone().isEmpty()){
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone.");
        }
        prompt.append("\nOriginal email: \n").append(emailRequest.getEmailContent());
        return prompt.toString();
    }

}
