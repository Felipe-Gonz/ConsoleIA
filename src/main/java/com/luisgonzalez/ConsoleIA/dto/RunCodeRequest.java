package com.luisgonzalez.ConsoleIA.dto;

import jakarta.validation.constraints.NotBlank;

public class RunCodeRequest {

    @NotBlank(message = "El codigo no puede estar vacio")
    private String code;
    private String input;

    public RunCodeRequest(){
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
