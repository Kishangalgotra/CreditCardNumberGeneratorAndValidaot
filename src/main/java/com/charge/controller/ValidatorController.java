package com.charge.controller;

import com.charge.response.ApplicationResponse;
import com.charge.services.ValidatorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v0/validator", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class ValidatorController {

    private final ValidatorService validatorService;

    ValidatorController(ValidatorService validatorService){
        this.validatorService = validatorService;
    }
    @GetMapping()
    public ResponseEntity<ApplicationResponse<String>> validateToken(@RequestParam("token") String token) {
        token = token.replaceAll("-","");
        return ResponseEntity.ok(ApplicationResponse.getSuccessResponse(validatorService.checkLuhn(token)));
    }
}
