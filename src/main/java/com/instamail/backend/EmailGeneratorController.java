package com.instamail.backend;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class EmailGeneratorController {


    private final EmailGeneratorService emailGeneratorService; // created obj of service class to access the responseEntity method



    // path -> ("/api/email/generate")
    // this is the post method to send post request and get the ai generated email reply as response
    // this is a method the returns ResponseEntity which contains string inside it
    // -> we created the method in the service class => private String extractResponseContent(String response)

    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@RequestBody EmailRequest emailRequest){ //EmailRequest is a DTO class with object emailRequest
       String response = emailGeneratorService.generateEmailReply(emailRequest); // response of the request sent by generateEmailReply method in service class will be stored in respose variable in string format
        return ResponseEntity.ok(response); //the response received from service class will be returned
    }
}
