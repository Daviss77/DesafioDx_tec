package br.com.duxusdesafio.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TimeResponseDto {

    private String nomeDoClube;
    private LocalDate data;
    private List<String> integrantes;

    public String getNomeDoClube() {
        return nomeDoClube;
    }

    public void setNomeDoClube(String nomeDoClube) {
        this.nomeDoClube = nomeDoClube;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<String> integrantes) {
        this.integrantes = integrantes;
    }
}
