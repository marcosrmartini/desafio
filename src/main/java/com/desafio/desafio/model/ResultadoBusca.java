package com.desafio.desafio.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResultadoBusca {

    @JsonProperty("listaControleLancamento")
    private List<ControleLancamento> lancamentoList;

    private Long quantidadeLancamentos;

    private Long quantidadeRemessas;

    private Double valorLancamentos;

    public List<ControleLancamento> getLancamentoList() {
        return lancamentoList;
    }

    public void setLancamentoList(List<ControleLancamento> lancamentoList) {
        this.lancamentoList = lancamentoList;
    }

    public Long getQuantidadeLancamentos() {
        return quantidadeLancamentos;
    }

    public void setQuantidadeLancamentos(Long quantidadeLancamentos) {
        this.quantidadeLancamentos = quantidadeLancamentos;
    }

    public Long getQuantidadeRemessas() {
        return quantidadeRemessas;
    }

    public void setQuantidadeRemessas(Long quantidadeRemessas) {
        this.quantidadeRemessas = quantidadeRemessas;
    }

    public Double getValorLancamentos() {
        return valorLancamentos;
    }

    public void setValorLancamentos(Double valorLancamentos) {
        this.valorLancamentos = valorLancamentos;
    }
}
