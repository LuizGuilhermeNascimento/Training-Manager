package com.projeto_mc322.api.dtos;

public class CreateProfessorDTO extends CreateUserDTO {
    private String creaf;

    public String getCreaf() {
        return creaf;
    }

    public void setCreaf(String creaf) {
        this.creaf = creaf;
    }
}
