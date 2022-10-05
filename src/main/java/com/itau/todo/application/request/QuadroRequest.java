package com.itau.todo.application.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class QuadroRequest {

    private String tipo;
    private String name;
}
