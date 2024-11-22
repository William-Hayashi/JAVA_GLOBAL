package br.com.fiap.demo.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;


@Entity
@Table(name = "TB_ENERGIA")
public class EnergiaModel extends RepresentationModel<EnergiaModel> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nomeUser;
    private String CPF;
    private String CEP;
    private String qntDeCarregadores;
    private String EstadoCarregadores;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getQntDeCarregadores() {
        return qntDeCarregadores;
    }

    public void setQntDeCarregadores(String qntDeCarregadores) {
        this.qntDeCarregadores = qntDeCarregadores;
    }

    public String getEstadoCarregadores() {
        return EstadoCarregadores;
    }

    public void setEstadoCarregadores(String estadoCarregadores) {
        EstadoCarregadores = estadoCarregadores;
    }
}
