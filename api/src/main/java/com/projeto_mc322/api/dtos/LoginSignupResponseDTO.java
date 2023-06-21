package com.projeto_mc322.api.dtos;

import com.projeto_mc322.api.models.user.Professor;
import com.projeto_mc322.api.models.user.User;

import java.util.UUID;

public class LoginSignupResponseDTO {
    private Integer role; // professor = 0, aluno = 1
    private UUID id;

    public LoginSignupResponseDTO(User user) {
        if (user instanceof Professor){
            this.role = 0;
        }else{
            this.role = 1;
        }
        this.id = user.getId();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
