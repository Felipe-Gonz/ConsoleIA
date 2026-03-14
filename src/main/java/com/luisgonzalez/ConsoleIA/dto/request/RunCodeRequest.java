package com.luisgonzalez.ConsoleIA.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RunCodeRequest {

    @NotBlank(message = "El codigo no puede estar vacio")
    @Size(max = 10000, message = "El codigo excede el tamaño maximo permitido")
    private String code;

    @Size(max = 10000, message = "El input excede el tamaño maximo permitido")
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
