package com.luisgonzalez.ConsoleIA.service;

import com.luisgonzalez.ConsoleIA.dto.RunCodeRequest;
import com.luisgonzalez.ConsoleIA.dto.RunCodeResponse;
import org.springframework.stereotype.Service;

@Service
public class CodeExecutionService {

    public RunCodeResponse executeCode(RunCodeRequest runCodeRequest){
        RunCodeResponse runCodeResponse = new RunCodeResponse();
        runCodeResponse.setOutput("Ejecucion simulada Correctamente");
        runCodeResponse.setError(null);
        runCodeResponse.setStatus("SUCCES");
        return runCodeResponse;
    }
}
