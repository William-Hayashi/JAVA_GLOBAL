package br.com.fiap.demo.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record EnergiaRecordDto(
        @JsonProperty("nomeUser")@NotNull String nomeUser,
        @JsonProperty("cpf") @NotNull String CPF,
        @JsonProperty("cep") @NotNull String CEP,
        @JsonProperty("qntDeCarregadores") @NotNull String qntDeCarregadores,
        @JsonProperty("estadoCarregadores") @NotNull String EstadoCarregadores
) {
}