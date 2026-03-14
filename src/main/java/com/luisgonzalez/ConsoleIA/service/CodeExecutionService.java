package com.luisgonzalez.ConsoleIA.service;

import com.luisgonzalez.ConsoleIA.dto.ProcessExecutionResult;
import com.luisgonzalez.ConsoleIA.dto.request.RunCodeRequest;
import com.luisgonzalez.ConsoleIA.dto.response.RunCodeResponse;
import com.luisgonzalez.ConsoleIA.util.FileManagerUtil;
import com.luisgonzalez.ConsoleIA.util.ProcessExecutorUtil;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class CodeExecutionService {

    private final FileManagerUtil fileManagerUtill;
    private final ProcessExecutorUtil processExecutorUtil;

    public CodeExecutionService(FileManagerUtil fileManagerUtil, ProcessExecutorUtil processExecutorUtil){
        this.fileManagerUtill = fileManagerUtil;
        this.processExecutorUtil = processExecutorUtil;
    }

    public RunCodeResponse executeCode(RunCodeRequest runCodeRequest){
        Path tempDir = null;

        try{
            tempDir = fileManagerUtill.createTempDirectory();
            fileManagerUtill.writeJavaFile(tempDir, runCodeRequest.getCode());

            ProcessExecutionResult compileResult = processExecutorUtil.compileJavaFile(tempDir);
            if (!"SUCESS".equals(compileResult.getStatus())){
                return mapToRunCodeResponse(compileResult);
            }
            ProcessExecutionResult runResult = processExecutorUtil.runJavaFile(tempDir, runCodeRequest.getInput());
            return mapToRunCodeResponse(runResult);
        } catch (Exception e){
            return new RunCodeResponse(null, e.getMessage(),"INTERNAL_ERROR", -1);
        } finally {
            if (tempDir != null){
                fileManagerUtill.deleteDirectory(tempDir);
            }
        }
    }

    private RunCodeResponse mapToRunCodeResponse(ProcessExecutionResult result){
        return new RunCodeResponse(
                result.getOutput(),
                result.getError(),
                result.getStatus(),
                result.getExitCode()
        );
    }
}
