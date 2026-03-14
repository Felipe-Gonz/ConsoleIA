package com.luisgonzalez.ConsoleIA.controller;

import com.luisgonzalez.ConsoleIA.dto.request.RunCodeRequest;
import com.luisgonzalez.ConsoleIA.dto.response.RunCodeResponse;
import com.luisgonzalez.ConsoleIA.service.CodeExecutionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CodeExecutionController {

    private CodeExecutionService codeExecutionService;

    public CodeExecutionController(CodeExecutionService codeExecutionService){
        this.codeExecutionService = codeExecutionService;
    }

    @GetMapping("/health")
    public String health(){
        return "API OK";
    }

    @PostMapping("/code/run")
    public RunCodeResponse runCode(@Valid @RequestBody RunCodeRequest runCodeRequest){
        return codeExecutionService.executeCode(runCodeRequest);
    }

}
