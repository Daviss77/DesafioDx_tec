package br.com.duxusdesafio.dto;

import java.time.LocalDate;
import java.util.List;

public class TimeDto {
    private String nomeDoClube;
    private LocalDate data;
    private List<Long> idsIntegrantes;

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

    public List<Long> getIdsIntegrantes() {
        return idsIntegrantes;
    }

    public void setIdsIntegrantes(List<Long> idsIntegrantes) {
        this.idsIntegrantes = idsIntegrantes;
    }
}
