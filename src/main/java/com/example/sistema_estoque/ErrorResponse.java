package com.example.sistema_estoque;

import java.time.LocalDateTime;

public class ErrorResponse {

    private int status;
    private String erro;
    private LocalDateTime data;

    public ErrorResponse(int status, String erro, LocalDateTime data){
        this.status = status;
        this.erro = erro;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getErro() {
        return erro;
    }
}
