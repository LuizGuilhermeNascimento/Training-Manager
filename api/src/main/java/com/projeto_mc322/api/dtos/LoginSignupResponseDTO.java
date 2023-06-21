package com.projeto_mc322.api.dtos;

import com.projeto_mc322.api.models.user.Professor;
import com.projeto_mc322.api.models.user.User;

import java.util.UUID;

public class LoginSignupResponseDTO {
    private String role;
    private UUID id;

    public LoginSignupResponseDTO(User user) {
        if (user instanceof Professor){
            this.role = "professor";
        }else{
            this.role = "aluno";
        }
        this.id = user.getId();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
