package br.projeto.petshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario extends DefaultEntity {

    @NotBlank(message = "O campo 'nome' não pode estar em branco")
    @Size(max = 100, message = "O campo 'nome' deve ter no máximo 100 caracteres")
    @Size(min = 3, message = "O campo 'nome' deve ter no mínimo 3 caracteres")
    private String nome;

    private String cpf;

    @NotBlank(message = "O campo 'username' não pode estar em branco")
    @Size(max = 50, message = "O campo 'username' deve ter no máximo 50 caracteres")
    private String username;

    @Email
    private String email;

    @NotBlank(message = "O campo 'senha' não pode estar em branco")
    private String senha;

    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private Perfil perfil;

    
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
