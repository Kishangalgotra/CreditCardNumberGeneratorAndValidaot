package com.charge.controller;

import com.charge.response.ApplicationResponse;
import com.charge.services.GeneratorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v0/generator", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class GeneratorController {

    private final GeneratorService generatorService;

    GeneratorController(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @GetMapping("/generate")
    public ResponseEntity<ApplicationResponse<String>> generate(@RequestParam("tokenDigits") String tokenDigits) {
        String generatedToken = generatorService.generate(tokenDigits);
        System.out.println(generatedToken);
        generatedToken = generatedToken.substring(0,4)+ "-"
                + generatedToken.substring(4,8)+ "-"
                + generatedToken.substring(8,12)+ "-"
                + generatedToken.substring(12);
        return ResponseEntity.ok(ApplicationResponse.getSuccessResponse(generatedToken));
    }
}
